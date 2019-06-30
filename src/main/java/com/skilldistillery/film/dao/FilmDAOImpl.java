package com.skilldistillery.film.dao;

import java.sql.*;
import java.util.*;



import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

public class FilmDAOImpl implements FilmDAO{
	private static String url = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	private final String user = "student", pass = "student";
	
	public FilmDAOImpl() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Film> getFilmByKeyword(String keyword) {
		List<Film> films = new ArrayList<>();
		Film film = null;
		String sql = "SELECT f.id, f.title, f.description, f.release_year, f.language_id, f.rental_duration,"
				+ " f.rental_rate, f.length, f.replacement_cost, f.rating, f.special_features, l.name FROM film f"
				+ " JOIN language l on l.id = f.language_id WHERE title LIKE ? OR description LIKE ?";
		
		try(Connection conn = DriverManager.getConnection(url, user, pass);
				PreparedStatement pstmt = conn.prepareStatement(sql);){
			
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setString(2, "%" + keyword + "%");
			
			ResultSet filmResult = pstmt.executeQuery();
			
			while (filmResult.next()) {
				
				film = setFilmData(film, filmResult);

				if (film != null) {
					System.out.println(film);
					film.setFilmActors(getActorsByFilmId(film.getId()));
					films.add(film);
				}
				
			}
			filmResult.close();
			
			
		}catch (SQLException e) {
			
			e.printStackTrace();
		}
		return films;
	}


	@Override
	public List<Actor> getActorsByFilmId(int filmId) {
		List<Actor> filmActors = new ArrayList<>();
		Actor actor = null;
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "select a.first_name, a.last_name, a.id "
					+ "from film f join film_actor fa on fa.film_id = f.id "
					+ "join actor a on a.id = fa.actor_id where f.id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, filmId);
			ResultSet actorByFilmResult = pstmt.executeQuery();

			while (actorByFilmResult.next()) {
				if (actorByFilmResult.next()) {
					actor = new Actor();
					actor.setId(actorByFilmResult.getInt("a.id"));
					actor.setFirstName(actorByFilmResult.getString("a.first_name"));
					actor.setLastName(actorByFilmResult.getString("a.last_name"));
					filmActors.add(actor);
					System.out.println(actor);
				}
			}
			actorByFilmResult.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return filmActors;
	}


	@Override
	public Film getFilmById(Integer filmId) {
		Film film = null;
		String sql = "SELECT f.id, f.title, f.description, f.release_year, f.language_id, f.rental_duration,"
				+ " f.rental_rate, f.length, f.replacement_cost, f.rating, f.special_features, l.name FROM film f"
				+ " JOIN language l on l.id = f.language_id WHERE f.id = ?";
		
		try (Connection conn = DriverManager.getConnection(url, user, pass);
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			
			pstmt.setInt(1, filmId);
			
			ResultSet filmResult = pstmt.executeQuery();
			
			film = setFilmData(film, filmResult);

			if (film != null) {
				System.out.println(film);
				film.setFilmActors(getActorsByFilmId(filmId));
				
			} 
			
			filmResult.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		System.out.println(film);
		return film;
	}
	
	public Film setFilmData(Film film, ResultSet rs) {
		try {
			if (rs.next()) {
				film = new Film();
				film.setId(rs.getInt("f.id"));
				film.setTitle(rs.getString("f.title"));
				film.setDescription(rs.getString("f.description"));
				film.setReleaseYear(rs.getInt("f.release_year"));
				film.setLangId(rs.getInt("f.language_id"));
				film.setRentalDuration(rs.getInt("f.rental_duration"));
				film.setLength(rs.getInt("f.length"));
				film.setRentalRate(rs.getDouble("f.rental_rate"));
				film.setReplacementCost(rs.getDouble("f.replacement_cost"));
				film.setRating(rs.getString("f.rating"));
				film.setSpecialFeatures(rs.getString("f.special_features"));	
				film.setLanguage(rs.getString("l.language.name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return film;
	}
	
	
}
