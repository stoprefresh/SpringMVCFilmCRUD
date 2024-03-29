package com.skilldistillery.film.dao;


import java.sql.*;
import java.util.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;


import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;


@Repository
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
	public Film updateFilm(Integer filmId, Film film) {
		Film f = film;
		String sql = "UPDATE film SET title='?', description='?', release_year=?, language_id=?, rental_duration=?, "
				+ "rental_rate=?, length=?, replacement_cost=?, rating='?', special_features='?' " + "WHERE id =?";
		
		try (Connection conn = DriverManager.getConnection(url, user, pass);
				PreparedStatement pstmt = conn.prepareStatement(sql);) {

			pstmt.setString(1, f.getTitle());
			pstmt.setString(2, f.getDescription());
			pstmt.setInt(3, f.getReleaseYear());
			pstmt.setInt(4, f.getLangId());
			pstmt.setInt(5, f.getRentalDuration());
			pstmt.setDouble(6, f.getRentalRate());
			pstmt.setInt(7, f.getLength());
			pstmt.setDouble(8, f.getReplacementCost());
			pstmt.setString(9, f.getRating());
			pstmt.setString(10, f.getSpecialFeatures());
			pstmt.setInt(11, filmId);

			pstmt.executeUpdate();

			return getFilmById(filmId);

		} catch (SQLException e) {
			e.printStackTrace();
			return f;
		}
	}
	
	@Override
	public Film addFilm(Film film) {
		Film f = film;
		String sql = "INSERT INTO film (title, description, release_year, language_id, rental_duration,"
				+ " rental_rate, length, replacement_cost, rating)"
				+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
//		String sqlCheck = "SELECT LAST_INSERT_ID()";
		int newID = 0;

		try (Connection conn = DriverManager.getConnection(url, user, pass);
				PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//				PreparedStatement checkSQL = conn.prepareStatement(sqlCheck);
				) {
//			conn.setAutoCommit(false);

			pstmt.setString(1, f.getTitle());
			pstmt.setString(2, f.getDescription());
			pstmt.setInt(3, f.getReleaseYear());
			pstmt.setInt(4, f.getLangId());
			pstmt.setInt(5, f.getRentalDuration());
			pstmt.setDouble(6, f.getRentalRate());
			pstmt.setInt(7, f.getLength());
			pstmt.setDouble(8, f.getReplacementCost());
			pstmt.setString(9, f.getRating());
			
			pstmt.executeUpdate();

			ResultSet keys = pstmt.getGeneratedKeys();
			while (keys.next()) {
				int newId = keys.getInt(1);
				System.out.println(newId);
				newID = newId;
			}
//			conn.commit();


		} catch (SQLException e) {
			e.printStackTrace();
			return f;
		}
		return getFilmById(newID);
	}
	
	

	@Override
	public void removeFilmById(Integer filmId) {
		String sql = "DELETE FROM film where id = ?";
		try (Connection conn = DriverManager.getConnection(url, user, pass);
				PreparedStatement pstmt = conn.prepareStatement(sql);) {

			pstmt.setInt(1, filmId);
			pstmt.executeUpdate();

		} catch (SQLException e) {
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

		try (Connection conn = DriverManager.getConnection(url, user, pass);
				PreparedStatement pstmt = conn.prepareStatement(sql);) {

			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setString(2, "%" + keyword + "%");

			ResultSet filmResult = pstmt.executeQuery();

			while (filmResult.next()) {

				film = setFilmData(film, filmResult);

				if (film != null) {

					film.setFilmActors(getActorsByFilmId(film.getId()));
					films.add(film);
				}

			}
			filmResult.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return films;
	}

	@Override
	public List<Actor> getActorsByFilmId(Integer filmId) {
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

				film.setFilmActors(getActorsByFilmId(filmId));

			}

			filmResult.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

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
				film.setLanguage(rs.getString("l.name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}


		return film;

	}
}
