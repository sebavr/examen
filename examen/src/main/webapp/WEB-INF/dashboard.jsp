<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<link rel="stylesheet" href="/style.css">

<meta charset="UTF-8">
<title>Shows</title>
</head>
<body>
<div id="container">
		<h1>Welcome ${user.name}</h1>
	<h6><a class="fltright" href="/logout">Logout</a></h6>
	
	<h3>Tv Shows</h3>
	

	
	<table  class="table table-striped table-bordered table-hover">
		<tr>
			<th>Show</th>
			<th>Network</th>
			<th> Avg Rating</th>
			
		</tr>
		<c:forEach items="${allShows}" var="show">	
		<tr>
			<td>
				<a href="/shows/${show.id}">
					${show.title}
				</a>
			</td>
			<td>${show.network}</td>
			<td>-</td>
			
			
			
		</tr>
		</c:forEach>
	</table>
	
	<!-- create new idea -->
	<a href="/shows/new">Add a show</a>
	</div>
</body>
</html>