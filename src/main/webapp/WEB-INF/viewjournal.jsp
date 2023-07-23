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
		          <a class="nav-link" href="/communityposts">Community</a>
		        </li>
		        <li class="nav-item">
		          <a class="nav-link" href="/logout">Logout</a>
		        </li>
		      </ul>
		    </div>
		  </div>
		</nav>

		<div class="card-container">
		
			<div></div>
			
			<div>
			
				<h3 class="mb-4">
					<c:out value="${journal.journalTitle}"></c:out>
				</h3>
		
				<div class="card p-3 mb-3">
				
					<p>
						Entry: <br />
						<c:out value="${journal.entry}"></c:out>
					</p>
					
					<p>
						Privacy:
						<c:out value="${journal.privacy}"></c:out>
					</p>
					
					<c:if test = "${journal.journaler.id==userId}">
						<div class="text-end">
							<a class="btn btn-primary me-2" href="/journal/edit/${journal.id}">Edit</a>
					    	<a class="btn btn-danger" href="/journal/delete/${journal.id}">Delete</a>
						</div>
					</c:if>
				
				</div>
				
				<div>
				
					<c:if test="${journal.privacy==0}">
					
						<div class="text-end">
							<a href="/comments/new">Add Comment</a>
						</div>
					
					</c:if>
					
					<c:forEach var="comment" items="${assignedComments}">
						<c:if test="${journal.privacy==0}">
						
							<table>
							    <tbody>
							    
							    	<tr>
							        	<td class="float-left">${comment.commenter.firstName}:</td>
							        	<td><c:out value="${comment.commentPost}"></c:out></td>
							       	<c:if test = "${comment.commenter.id==user.id}">
								       <td><a href="/editcomment/${comment.id}">Edit</a></td>
								       <td><a href="/comment/delete/${comment.id}">Delete</a></td>
								    </c:if>
							        </tr>
							        
							    </tbody>
							</table>
						</c:if>
							
					</c:forEach>
					
				</div>
				
			</div>
		
		</div>
		
		<footer class="text-center">
			<small>&copy; 2023 Angel and Jkligel</small>
		</footer>
	
	</div>

	<!-- For any Bootstrap that uses JS -->
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>