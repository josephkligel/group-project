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
</head>
<body>
	<div class="container">
	
		<nav class="navbar navbar-expand-lg bg-body-tertiary mb-3">
		  <div class="container-fluid">
		    <a class="navbar-brand" href="#">InnerGuru</a>
		    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
		      <span class="navbar-toggler-icon"></span>
		    </button>
		    <div class="collapse navbar-collapse" id="navbarNav">
		      <ul class="navbar-nav text-end">
		        <li class="nav-item">
		          <a class="nav-link" aria-current="page" href="/home">Home</a>
		        </li>
		        <li class="nav-item">
		        	<a class="nav-link" href="javascript:history.back()">Go Back</a>
		        </li>
		        <li class="nav-item">
		          	<a class="nav-link active" href="/journals/new">Add New Journal</a>
		        </li>
		        <li class="nav-item">
		          <a class="nav-link" href="/goals/new">Add New Goal</a>
		        </li>
		        <li class="nav-item">
		          <a class="nav-link" href="/communityposts">Community</a>
		        </li>
		        <li class="nav-item">
		          <a class="nav-link" href="/logout">Logout</a>
		        </li>
		      </ul>
		    </div>
		  </div>
		</nav>

		
		<div class="form-container">
			<div></div>
			
			<form:form class="mb-5" action="/journals/new" method="post" modelAttribute="journal">
				
					<h3 class="mb-5">Add a Journal Entry:</h3>
					
					<form:errors path="user" class="error"/>
					<form:input type="hidden" path="user" value="${userId}" class="form-control"/>
				
					<div class="row mb-3">
						<form:label class="col-form-label col-sm-3" path="journalTitle">Journal Title:</form:label>
						<form:input class="col-sm-9" path="journalTitle"/>
						<form:errors path="journalTitle" class="text-danger"/>
					</div>
					
					<div class="row mb-3">
						<form:label class="form-label col-sm-3" path="privacy">Privacy:</form:label>
						<div class="col-sm-9">
							<div class="form-check form-check-inline">
								<form:label for="priv" class="form-check-label" path="privacy">Public</form:label>
								<form:radiobutton class="form-check-input" name="priv" value= "0" path="privacy"/>
				            	<form:errors path="privacy" class="text-danger"/>
							</div>
			            	
							<div class="form-check form-check-inline">
								<form:label for="priv" class="form-check-label" path="privacy">Private</form:label>
								<form:radiobutton class="form-check-input" name="priv"  value= "1" path="privacy"/>
				            	<form:errors path="privacy" class="text-danger"/>
							</div>
							
						</div>
						
					</div>
					
					<div class="row mb-3">
						<form:label class="col-form-label col-sm-3" path="entry">Entry:</form:label>
						<form:textarea rows="7" class="col-sm-9" path="entry"/>
						<form:errors path="entry" class="text-danger"/>
					</div>
					
					<div class="text-end">
						<a class="btn btn-danger me-3" href="/home">Cancel</a>
						<input class="btn btn-success" type="submit" value="Create"/>
					</div>
					    
			</form:form>
		
		</div>
			
		<footer class="text-center">
			<small>&copy; 2023 Angel and Jkligel</small>
		</footer>
	
	</div>
	<!-- For any Bootstrap that uses JS -->
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>	
</body>
</html>