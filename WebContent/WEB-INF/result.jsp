<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Films</title>
</head>
<body>
  <c:choose> 
    <c:when test="${! empty films}">
    	<c:forEach var="films" items="${films }">
	      <ul>
	        <li>${films.title}</li>
	        <li>${films.description}</li>
	        <li>${films.releaseYear}</li>
	        <li>${films.rating}</li>
	        <li>${films.specialFeatures}</li>
	      </ul>
	     <%--  <c:forEach var="actor" items="${films.filmActors }">
	      	<ul>
	      		<li>${actor.first_name }</li>
	      		<li>${actor.last_name }</li>
	      	</ul>
	      </c:forEach> --%>
	      
      </c:forEach>c:forEach>
    </c:when>
    <c:when test="${! empty film}">
	      <ul>
	        <li>${films.title}</li>
	        <li>${films.description}</li>
	        <li>${films.releaseYear}</li>
	        <li>${films.rating}</li>
	        <li>${films.specialFeatures}</li>
	      </ul>
    </c:when>
    <c:otherwise>
      <p>No films found</p>
    </c:otherwise>
  </c:choose>
</body>
</html>