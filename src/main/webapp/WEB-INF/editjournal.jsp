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
		
		<form:form class="mb-5" action="/journal/edit/${id}" method="post" modelAttribute="journal">
			
				<h3 class="mb-5">Edit a Journal Entry:</h3>
				
				<form:errors path="journaler" class="error"/>
				<form:input type="hidden" path="journaler" value="${userId}" class="form-control"/>
				
				<input type="hidden" name="_method" value="put" />
			
				<div class="row mb-3">
					<form:label class="col-form-label col-sm-3" path="journalTitle">Journal Title:</form:label>
					<form:input class="col-sm-5" path="journalTitle"/>
					<form:errors path="journalTitle" class="text-danger"/>
				</div>
				
				<div class="row mb-3">
					<form:label class="form-label col-sm-3" path="privacy">Privacy:</form:label>
					<div class="col-sm-5">
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
					<form:textarea rows="7" class="col-sm-5" path="entry"/>
					<form:errors path="entry" class="text-danger"/>
				</div>
				
				<div class="col-sm-8 text-end">
					<a class="btn btn-danger me-3" href="/home">Cancel</a>
					<input class="btn btn-success" type="submit" value="Update"/>
				</div>
				    
			</form:form>
	
	</div>
	
</body>
</html>