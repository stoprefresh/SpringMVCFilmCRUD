<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Films with Friends</title>
</head>
<body>
	<form:form action="GetFilmByKeyword.do" method="GET">
		<input type="submit" value="GET FILM BY KEYWORD" />
	</form:form>
	<form:form action="GetFilmById.do" method="GET">
		<input type="submit" value="GET FILM BY ID" />
	</form:form>
	<form:form action="AddNewFilm.do" method="GET">
		<input type="submit" value="ADD NEW FILM" />
	</form:form>
</body>
</html>