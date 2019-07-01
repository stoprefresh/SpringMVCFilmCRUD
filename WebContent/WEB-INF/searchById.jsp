<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Films</title>
</head>
<body>
	<form:form action="GetFilmById.do" method="POST" modelAttribute="search">
		<h1>Get Film by FilmID</h1>
		<form:label path="id">Film ID:</form:label> 
		<form:input path="id" /> 
		<input type="submit" value="Get Film Data" aria-labeled="SUBMIT" />
	</form:form>
</body>
</html>