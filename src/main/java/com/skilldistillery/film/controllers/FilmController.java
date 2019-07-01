package com.skilldistillery.film.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skilldistillery.film.dao.FilmDAO;
import com.skilldistillery.film.entities.Film;

@Controller
public class FilmController {
	
	@Autowired
	private FilmDAO filmDao;

	public void setFilmDAO(FilmDAO filmDao) {
		this.filmDao = filmDao;
	}
	
	
	
	@RequestMapping(path="GetFilmByKeyword.do", params="keyword",  method = RequestMethod.GET)
	public ModelAndView getFilmByKeyWord(String keyword) {
		ModelAndView mv = new ModelAndView();
		List<Film> films = filmDao.getFilmByKeyword(keyword);
		mv.addObject("films", films);
		mv.setViewName("result");
		return mv;
	}
	
	@RequestMapping(path="GetFilmById.do", params = "id", method = RequestMethod.GET)
	public ModelAndView getFilmById(Integer id) {
		ModelAndView mv = new ModelAndView();
		Film film = filmDao.getFilmById(id);
		mv.addObject("film", film);
		mv.setViewName("result");
		return mv;
	}
}
