<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<title>New Goal</title>
</head>
<body>

<h3><a href="/home">Back</a></h3>


<h1>Add a New Goal</h1>

<form:form action="/goals/new" method="post" modelAttribute="goal">

	<table>
	    <thead>
	    	<tr>
	            <td class="float-left">Goal Name:</td>
	            <td class="float-left">
	            	<form:errors path="goalName" class="text-danger"/>
					<form:input class="input" path="goalName"/>
	            </td>
	        </tr>
	        
	        <tr>
	            <td class="float-left">Goal Length:</td>
	            <td class="float-left">
	            	<form:errors path="shortOrLong" class="text-danger"/>
					<form:input path="shortOrLong" class="text-danger"/>
	            </td>
	        </tr>
	      	
	      	<tr>
	            <td class="float-left">Description:</td>
	            <td class="float-left">
	            	<form:errors path="description" class="text-danger"/>
					<form:textarea path="description" class="text-danger"/>
	            </td>
	        </tr>
	       
	        
	        <form:errors path="aspirer" class="error"/>
			<form:input type="hidden" path="aspirer" value="${userId}" class="form-control"/>
			
	        <tr>
	        	<td><a class="linkBtn" href="/home">Cancel</a></td>
	        	<td><input class="input" type="submit" value="Save"/></td>
	        </tr>
	        
	    </thead>
	</table>
</form:form>
</body>
</html>