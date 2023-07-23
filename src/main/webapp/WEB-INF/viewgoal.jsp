<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<title>Journal Details</title>
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
				<a href="javascript:history.back()">Go Back</a>
			    <a class="" href="/home">Home</a>
	           	<a href="/logout">Logout</a>
		    </div>
			
		</nav>
		
		<div>
		
			<h3>
				<c:out value="${goal.goalName}"></c:out>
			</h3>
		
			<div class="card p-3">
			
				<p>
					Goal Length:
					<c:out value="${goal.shortOrLong}"></c:out>
				</p>
				
				<p>
					Description:
					<c:out value="${goal.description}"></c:out>
				</p>
				
				<c:if test = "${goal.aspirer.id==userId}">
					<div class="text-end">
						<a class="btn btn-primary me-2" href="/goal/edit/${goal.id}">Edit</a>
					    <a class="btn btn-danger" href="/goal/delete/${goal.id}">Delete</a>					
					</div>
				</c:if>
			
			</div>

		</div>
		
		<footer class="text-center">
			<small>&copy; 2023 Angel and Jkligel</small>
		</footer>
	
	</div>


</body>
</html>