<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<title>New Journal</title>
<!-- for Bootstrap CSS -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- YOUR own local CSS -->
<link rel="stylesheet" href="/css/main.css"/>
<!-- For any Bootstrap that uses JS -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
	
		<nav class="navbar navbar-expand-lg mb-4">
		
			
			<div class="navbar-header">
				<a class="navbar-brand" href="#">InnerGuru</a>
			</div>
			
			<div>
			    <a class="" href="/home">Home</a>
	           	<a href="/logout">Logout</a>
		    </div>
			
		</nav>
		
		<form:form class="mb-5" action="/goals/new" method="post" modelAttribute="goal">
			
				<h3 class="mb-5">Add a Goal:</h3>
				
				<form:errors path="aspirer" class="error"/>
				<form:input type="hidden" path="aspirer" value="${userId}" class="form-control"/>
			
				<div class="row mb-3">
					<form:label class="col-form-label col-sm-3" path="goalName">Goal Name:</form:label>
					<form:input class="col-sm-5" path="goalName"/>
					<form:errors path="goalName" class="text-danger"/>
				</div>
				
				<div class="row mb-3">
					<form:label class="col-form-label col-sm-3" path="shortOrLong">Goal Length:</form:label>
					<form:input class="col-sm-5" path="shortOrLong"/>
					<form:errors path="shortOrLong" class="text-danger"/>
				</div>
				
				<div class="row mb-3">
					<form:label class="col-form-label col-sm-3" path="description">Description:</form:label>
					<form:input class="col-sm-5" path="description"/>
					<form:errors path="description" class="text-danger"/>
				</div>
				
				<div class="col-sm-8 text-end">
					<a class="btn btn-danger me-3" href="/home">Cancel</a>
					<input class="btn btn-success" type="submit" value="Update"/>
				</div>
				    
			</form:form>
	
	</div>
	
</body>
</html>