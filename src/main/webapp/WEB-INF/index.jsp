<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<title>Register</title>
<!-- for Bootstrap CSS -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- YOUR own local CSS -->
<link rel="stylesheet" href="/css/main.css"/>
<!-- For any Bootstrap that uses JS -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>

	<div class="container">
	
		<nav class="navbar navbar-expand-lgmb-4">
			<h1 class="navbar-brand">Welcome to InnerGuru!</h1>
		</nav>

		<div class="grid-container pt-3">
	
			<form:form class="mb-3" action="/register" method="post" modelAttribute="newUser">
			
				<h3 class="mb-4">Register New User:</h3>
			
				<div class="row mb-3">
					<form:label class="col-form-label col-sm-3" path="firstName">First Name:</form:label>
					<form:input class="col-sm-5" path="firstName"/>
					<form:errors path="firstName" class="text-danger"/>				
				</div>
				
				<div class="row mb-3">
					<form:label class="col-form-label col-sm-3" path="lastName">Last Name:</form:label>
					<form:input class="col-sm-5" path="lastName"/>
					<form:errors path="lastName" class="text-danger"/>
				</div>
				    
				
				<div class="row mb-3">
					<form:label class="col-form-label col-sm-3" path="email">Email:</form:label>
					<form:input type="email" class="col-sm-5" path="email"/>
					<form:errors path="email" class="text-danger"/>
				</div>  
				
				
				<div class="row mb-3">
					<form:label class="col-form-label col-sm-3" path="password">Password:</form:label>
					<form:password class="col-sm-5" path="password"/>
					<form:errors path="password" class="text-danger"/>
				</div>
				
				<div class="row mb-3">
					<form:label class="col-form-label col-sm-3" path="confirm">Confirm Password:</form:label>
					<form:password class="col-sm-5" path="confirm"/>      
					<form:errors path="confirm" class="text-danger"/>
				</div>
			
				<div class="col-sm-8 text-end">
					<input class="btn btn-success" type="submit" value="Register"/>
				</div>
			
			</form:form>
			
			<form:form class="mb-5" action="/login" method="post" modelAttribute="newLogin">
			
				<h3 class="mb-4">Login:</h3>
			
				<div class="row mb-3">
					<form:label class="col-form-label col-sm-3" path="email">Email:</form:label>
					<form:input class="col-sm-5" path="email"/>
					<form:errors path="email" class="text-danger"/>
				</div>
				
				<div class="row mb-3">
					<form:label class="col-form-label col-sm-3" path="password">Password:</form:label>
					<form:password class="col-sm-5" path="password"/>
					<form:errors path="password" class="text-danger"/>
				</div>
			
				<div class="col-sm-8 text-end">
					<input class="btn btn-primary" type="submit" value="Login"/>
				</div>
				    
			</form:form>

		</div>
		
		<footer class="text-center">
			<small>&copy; 2023 Angel and Jkligel</small>
		</footer>
		
	</div>	

</body>
</html>