<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
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
		<h1>Welcome </h1>

	<div id="leftpanel">
	<p><c:out value="${error}" /></p>
	
	<!-- Register -->
	<h1>Register!</h1>
    
    <p><form:errors path="user.*"/></p>
    
    <form:form method="POST" action="/registration" modelAttribute="user">
    	<h4 class="avoid">
					<form:label path="name">Name:</form:label>
					<form:input type="text" path="name" />
		</h4>
       <h4 class="avoid">
            <form:label path="email">Email:</form:label>
            <form:input type="email" path="email"/>
       </h4>
        <h4 class="avoid">
            <form:label path="password">Password:</form:label>
            <form:password path="password"/>
       </h4>
        <h4 class="avoid">
            <form:label path="passwordConfirmation">Password Confirmation:</form:label>
            <form:password path="passwordConfirmation"/>
       </h4>
        <input type="submit" value="Register"/>
    </form:form>
    </div>
    
    <div id="rightpanel">
    <!-- Login -->
     <h1>Login</h1>
    <p><c:out value="${error}" /></p>
    <form method="post" action="/login">
        <h4>
            <label for="email">Email</label>
            <input type="text" id="email" name="email"/>
        </h4>
        <h4>
            <label for="password">Password</label>
            <input type="password" id="password" name="password"/>
        </h4>
        <input type="submit" value="Login"/>
    </form>   
    </div> 
    </div>
</body>
</html>