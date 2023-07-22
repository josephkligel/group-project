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
</head>
<body>

<div class="top">
        <div class="nav">
            <h2 class="nav-title">InnerGuru</h2>
            <ul class="nav-links">
            	<li><a href="/home">Home</a></li>
                <li><a href="/journals/new">Add Journal</a></li>
                <li><a href="/goals/new">Add Goal</a></li>
                <li><a href="/communityposts">Community</a></li>
                <li><a href="/logout">Logout</a></li>
            </ul>
        </div>  
</div>

<h4>Explore Community Posts:</h4>

<table>
    <thead> 
    	<tr>
    		<th>Journal Title</th>
    		<th>Date Created</th>
    		<c:if test = "${journal.journalist.id==user.id}">
    			<th colspan="3">Actions</th>
    		</c:if>
    	</tr>
    </thead>
    <tbody>
    	<c:forEach var="journal" items="${allJournals}">
    		<c:if test = "${journal.privacy==0}">
				<tr>
					<td><a href="/journals/${journal.id}">${journal.journalTitle}</a></td>
					<td><c:out value="${journal.createdAt}"></c:out></td>
					<c:if test = "${journal.journaler.id==user.id}">
						<td><a href="/journal/edit/${journal.id}">Edit</a></td>
				       	<td><a href="/journal/delete/${journal.id}">Delete</a></td>
				    </c:if>
				</tr>	
			</c:if>
		</c:forEach>
		
    </tbody>
</table>

</body>
</html>