<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Comment</title>
<link rel="stylesheet" type="text/css" href="/css/style.css">

</head>
<body>
<h2>Create Comment</h2>
	<form:form action="/comments/new" method="post" modelAttribute="comment">
	
		<table>
		    <thead>
		    	<tr>
		            <td class="float-left">New Comment:</td>
		            <td class="float-left">
		            	<form:errors path="commentPost" class="text-danger"/>
						<form:textarea class="textarea" path="commentPost"/>
		            </td>
		        </tr>
		        
		       
		        <form:errors path="journaler" class="error"/>
				<form:input type="hidden" path="journaler" value="${userId}" class="form-control"/>
				
		        <tr>
		        	<td><a class="linkBtn" href="/home">Cancel</a></td>
		        	<td><input class="input" type="submit" value="Save"/></td>
		        </tr>
		        
		    </thead>
		</table>
	</form:form>
</body>
</html>