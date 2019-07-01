<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Films</title>
</head>
<body>
	<c:choose>
		<c:when test="${! empty films}">
			<c:forEach var="f" items="${films }">
				<ul>
					<li>${f.title}</li>
					<li>${f.description}</li>
					<li>${f.releaseYear}</li>
					<li>${f.rating}</li>
					<%-- <li>${f.specialFeatures}</li> --%>
				</ul>
				<c:forEach var="a" items="${f.filmActors}">
					<ul>
						<li>${a.firstName }</li>
						<li>${a.lastName }</li>
					</ul>
				</c:forEach>
				<form action="GetDelete.do" method="POST">
					<input type="submit" value="Delete Film" />
				</form>

			</c:forEach>
		</c:when>
		<c:when test="${! empty film}">
			<ul>
				<li>${film.title}</li>
				<li>${film.description}</li>
				<li>${film.releaseYear}</li>
				<li>${film.rating}</li>
				<form action="GetDelete.do" method="POST">
					<input type="submit" value="Delete Film" />
				</form>
				<%-- <li>${film.specialFeatures}</li> --%>
			</ul>
		</c:when>
		<c:otherwise>
			<p>No films found</p>
		</c:otherwise>
	</c:choose>
	<br>

</body>
</html>