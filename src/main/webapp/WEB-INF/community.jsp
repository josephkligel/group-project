<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<title>Community Page</title>
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
		          	<a class="nav-link" href="/journals/new">Add New Journal</a>
		        </li>
		        <li class="nav-item">
		          <a class="nav-link" href="/goals/new">Add New Goal</a>
		        </li>
		        <li class="nav-item">
		          <a class="nav-link active" href="/communityposts">Community</a>
		        </li>
		        <li class="nav-item">
		          <a class="nav-link" href="/logout">Logout</a>
		        </li>
		      </ul>
		    </div>
		  </div>
		</nav>
		
		<div>
			
			<h3 class="mb-4">Explore Community Posts:</h3>
			
			<table class="table">
			    <thead class="table-dark"> 
			    	<tr>
			    		<th>Journal Title</th>
			    		<th>Date Created</th>
			    		<th>Actions</th>
			    	</tr>
			    </thead>
			    <tbody>
			    	<c:forEach var="journal" items="${allJournals}">
			    		<c:if test = "${journal.privacy==0}">
							<tr>
								<td><a class="text-success" href="/journals/${journal.id}">${journal.journalTitle}</a></td>
								<td><c:out value="${journal.createdAt}"></c:out></td>
								<c:if test = "${journal.journaler.id==user.id}">
									<td>
										<a class="me-2" href="/journal/edit/${journal.id}">Edit</a>
							       		<a class="text-danger" href="/journal/delete/${journal.id}">Delete</a>
							       	</td>
							    </c:if>
							</tr>	
						</c:if>
					</c:forEach>
					
			    </tbody>
			</table>
		
		</div>
		
		<footer class="text-center">
			<small>&copy; 2023 Angel and Jkligel</small>
		</footer>
		
	</div>

	
	<!-- For any Bootstrap that uses JS -->
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>