<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/style.css">
<meta charset="ISO-8859-1">
<title>Ideas</title>
</head>
<body>
		<h1>Edit: ${show.title}</h1>
	<c:out value="${error}" />
	<form:form action="/shows/${show.id}/edit" method="post" modelAttribute="updateShow">
			<input type="hidden" name="content" value="">
		
		<p>
			<form:label path="title">Title: </form:label>
			<form:input path="title" placeholder="${show.title}" />
		</p>
		<p>
			<form:errors path="title" />
		</p>
		
			<p>
			<form:label path="network">Network: </form:label>
			<form:input path="network" placeholder="${show.network}" />
		</p>
		<p>
			<form:errors path="network" />
		</p>
		
		
		
			<input type="submit" value="Update" />
		</form:form>
		
		<form action="/shows/${show.id}/delete" method="post">
			<input type="hidden" name="content" value="delete">
			<input type="submit"  value="Delete">
		</form>
</body>
</html>