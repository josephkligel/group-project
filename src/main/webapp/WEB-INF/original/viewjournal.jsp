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
</head>
<body>

<h2><a href="/home">Back</a></h2>
<c:if test="${journal.privacy==0}">

<h2><a href="/comments/new">Add Comment</a></h2>

</c:if>


<h1>View Journal</h1>
<table>
    <tbody>
    
    	<tr>
        	<td class="float-left">Journal Title:</td>
        	<td colspan="2"><c:out value="${journal.journalTitle}"></c:out></td>
        </tr>
        
        <tr>
        	<td class="float-left">Entry:</td>
            <td colspan="2"><c:out value="${journal.entry}"></c:out></td>
        </tr>
        
        <tr>
        	<td class="float-left">Privacy:</td>
            <td><c:out value="${journal.privacy}"></c:out></td>
        </tr>
        
    </tbody>
</table>

<c:if test = "${journal.journaler.id==userId}">
	<h3><a href="/journal/edit/${journal.id}">Edit</a></h3>
    <h3><a href="/journal/delete/${journal.id}">Delete</a></h3>
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

</body>
</html>