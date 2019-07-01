package com.skilldistillery.film.entities;

public class Search {
	private String keyword;
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public Search() {}

	@Override
	public String toString() {
		return "Search [keyword=" + keyword + ", id=" + id + "]";
	}

	public Search(String keyword) {
		super();
		this.keyword = keyword;
	}
	
	public Search(Integer id) {
		super();
		this.id = id;
	}
	
	
}
