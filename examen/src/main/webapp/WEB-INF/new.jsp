<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/style.css">
<meta charset="ISO-8859-1">
<title>Shows</title>
</head>
<body>
		<h1>Create a new show</h1>
	<form:form action="/shows/new" method="post" modelAttribute="newShow">

		<p>
			<form:label path="title">Title: </form:label>
			<form:input path="title" placeholder="title" cssErrorClass="formFieldError"/>
		</p>
		<p>
			<form:errors path="title" />
		</p>
		
			<p>
			<form:label path="network">Network: </form:label>
			<form:input path="network" placeholder="network" cssErrorClass="formFieldError" />
		</p>
		<p>
			<form:errors path="network" />
		</p>
		<input type="submit" value="Create" />

	</form:form>
</body>
</html>