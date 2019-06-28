package com.skilldstillery.film.dao;

import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.film.entities.*;


public interface FilmDAO {
	
	public Film findFilmById(Integer filmId) throws SQLException;
	public Film findFilmByKeyword(String keyword) throws SQLException;
	public List<Actor> findActorsByFilmId(int filmId);

}
