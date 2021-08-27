<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<link rel="stylesheet" href="/style.css">

<meta charset="UTF-8">
<title>Ideas</title>
</head>
<body>
<div id="container">
	<h1>${show.title}</h1>
	
	
	<h2>Network: ${show.network}</h2>
	
	<h1>Users who rated this show</h1>
	<table  class="table table-striped table-bordered table-hover">
		<tr>
			<th>Name</th>
			<th>Rating</th>
		</tr>
		
		<c:forEach items="${rating}" var="rs">
		<c:if test ="${show.id==rs.show.id}">
		<tr>
			<td>
				${rs.user.name}
			</td>
			<td>
				${rs.number}
			</td>
		</tr>
		</c:if>
		</c:forEach>
		
	</table>
	
	<a href="/shows/${show.id}/edit">Edit</a>
	
	
	<form action="/shows/${show.id}/rate" method="post">
			<h2>Leave a Rating</h2>
			<label for="rate"></label>

<select name="num" >
  <option value="1">1</option>
  <option value="2">2</option>
  <option value="3">3</option>
  <option value="4">4</option>
   <option value="5">5</option>
</select>
			<button>Rate!</button>
		</form>
		
		
		
		
		
		</div>
</body>
</html>