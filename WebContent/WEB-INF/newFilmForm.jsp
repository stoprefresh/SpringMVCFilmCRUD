
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Films</title>
<link rel="stylesheet" href="newFilmForm.css">
</head>
<body>
	<form:form action="AddNewFilm.do" method="POST" modelAttribute="film">
		<form:label path="title">Title:</form:label>
		<form:input path="title"/>
		<form:errors path="title" />
		<br>
		<form:label path="description">Description:</form:label>
		<form:textarea path="description"></form:textarea>
		<form:errors path="description" />
		<br>
		<form:label path="releaseYear">Release year:</form:label>
		<form:input path="releaseYear"/>
		<form:errors path="releaseYear" />
		<br>
		<form:label path="langId">Language ID:</form:label>
		<form:input path="langId"/>
		<form:errors path="langId" />
		<br>
		<form:label path="rentalDuration">Rental Duration:</form:label>
		<form:input path="rentalDuration"/>
		<br>
		<form:label path="rentalRate">Rental Rate:</form:label>
		<form:input path="rentalRate"/>
		<form:errors path="title" />
		<br>
		<form:label path="length">Film  Length:</form:label>
		<form:input path="length"/>
		<br>
		<form:label path="replacementCost">Replacement Cost:</form:label>
		<form:input path="replacementCost"/>
		<br>
		<form:label path="rating">Film Rating:</form:label>
		<form:input path="rating"/>
		<form:errors path="rating" />
		<br>
		<form:label path="specialFeatures">Special Features:</form:label>
		<form:input path="specialFeatures"/>
		<br>		
		<input type="submit" value="SUBMIT"/>		
	</form:form>
</body>
</html>