package com.skilldistillery.film.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
import org.springframework.validation.Errors;


import com.skilldistillery.film.dao.FilmDAO;
import com.skilldistillery.film.dao.FilmDAOImpl;
import com.skilldistillery.film.entities.Film;
import com.skilldistillery.film.entities.Search;

@Controller
public class FilmController {
	
	@Autowired
	private FilmDAOImpl filmDao;

	public void setFilmDAO(FilmDAOImpl filmDao) {
		this.filmDao = filmDao;
	}

	@RequestMapping(path="GetFilmByKeyword", method = RequestMethod.GET)
	public ModelAndView getByKeyWord() {
		Search s = new Search();
		ModelAndView mv = new ModelAndView("searchByKeyword", "search", s);	
		return mv;
	}
	
	@RequestMapping(path="GetFilmByKeyword.do", method = RequestMethod.POST)
	public ModelAndView getFilmByKeyWord(Search s) {
		ModelAndView mv = new ModelAndView();
		List<Film> films = filmDao.getFilmByKeyword(s.getKeyword());
		mv.addObject("films", films);
		mv.setViewName("result");
		return mv;
	}
	
	@RequestMapping(path="GetFilmById", method = RequestMethod.GET)
	public ModelAndView getById(Search s) {
		Search sId = new Search();
		ModelAndView mv = new ModelAndView("searchById", "search", sId);
		
		return mv;
	}
	
	@RequestMapping(path="GetFilmById.do", method = RequestMethod.POST)
	public ModelAndView getFilmById(Search s) {
		ModelAndView mv = new ModelAndView();
		Film film = filmDao.getFilmById(s.getId());
		mv.addObject("film", film);
		mv.setViewName("result");
		return mv;
	}
	
	
	@RequestMapping(path="AddNewFilm", method = RequestMethod.GET)
	public ModelAndView addAFilm() {
		ModelAndView mv = new ModelAndView();
		Film film = new Film();
		
		mv.addObject("film", film);
		mv.setViewName("newFilmForm");
		return mv;
	}

	@RequestMapping(path="AddNewFilm.do", method = RequestMethod.POST)
	public ModelAndView addFilm(@Valid Film film, Errors errors) {
		ModelAndView mv = new ModelAndView();
		System.out.println("======================");
		System.out.println(film);
		Film f = filmDao.addFilm(film);
		System.out.println(f);
		mv.addObject("film", f);
		mv.setViewName("result");
		return mv;
	}

	@RequestMapping(path="GetDelete.do", method = RequestMethod.POST)
	public ModelAndView getDelete(Search s) {
		
		ModelAndView mv = new ModelAndView();
		filmDao.removeFilmById(s.getId());
		mv.setViewName("index");

		return mv;
	}
	
	
}
