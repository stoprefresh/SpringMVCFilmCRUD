package com.skilldistillery.film.dao;


import java.util.List;

import com.skilldistillery.film.entities.*;


public interface FilmDAO {
	
	
	public Film getFilmById(Integer filmId);
	public List<Film> getFilmByKeyword(String keyword);
	public List<Actor> getActorsByFilmId(Integer filmId);
	public void removeFilmById(Integer filmId);
	public Film addFilm(Film film);
	public Film updateFilm(Integer filmId, Film film);
}
