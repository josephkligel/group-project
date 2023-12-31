<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<title>User Home</title>
<!-- for Bootstrap CSS -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- YOUR own local CSS -->
<link rel="stylesheet" href="/css/main.css"/>
<!-- For any Bootstrap that uses JS -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<div class="top">
        <div class="nav">
            <h2 class="nav-title">InnerGuru</h2>
            <ul class="nav-links">
            	<li><a href="/home">Home</a></li>
            	<li><a href="/journals/new">Add New Journal</a></li>
                <li><a href="/goals/new">Add New Goal</a></li>
                <li><a href="/communityposts">Community</a></li>
                <li><a href="/logout">Logout</a></li>
            </ul>
        </div>  
</div>



<hr>
<h3>${user.firstName}'s Home</h3>
<table>
    <thead> 
    	<tr>
    		<th>Goal Name</th>
    		<th>Goal Length</th>
    		<th>Date Created</th>
    		<th colspan="2">Actions</th>
    	</tr>
    </thead>
    <tbody>
    	<c:forEach var="goal" items="${assignedGoals}">
			<tr>
				<td><a href="/goals/${goal.id}">${goal.goalName}</a></td>
				<td><c:out value="${goal.shortOrLong}"></c:out></td>
				<td><span style="font-size: 10px"><c:out value="${goal.createdAt}"></c:out></span></td>
				<c:if test = "${goal.aspirer.id==user.id}">
			       <td><a href="/goal/edit/${goal.id}">Edit</a></td>
			       <td><a href="/goal/delete/${goal.id}">Delete</a></td>
			    </c:if>
			   
			</tr>
		</c:forEach>
    </tbody>
</table>

<table>
    <thead> 
    	<tr>
    		<th>Journal Title</th>
    		<th>Date Created</th>
    		<th colspan="2">Actions</th>
    	</tr>
    </thead>
    <tbody>
    	<c:forEach var="journal" items="${assignedJournals}">
			<tr>
				<td><a href="/journals/${journal.id}">${journal.journalTitle}</a></td>
				<td><span style="font-size: 10px"><c:out value="${journal.createdAt}"></c:out></span></td>
				<c:if test = "${journal.journaler.id==user.id}">
			       <td><a href="/journal/edit/${journal.id}">Edit</a></td>
			       <td><a href="/journal/delete/${journal.id}">Delete</a></td>
			    </c:if>
			   
			</tr>
		</c:forEach>
    </tbody>
</table>
</body>
</html>