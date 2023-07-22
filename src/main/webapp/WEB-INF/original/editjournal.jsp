<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<title>Edit Journal</title>
</head>
<body>

<h3><a href="/home">Back</a></h3>


<h1>Edit Journal</h1>

<form:form action="/journal/edit/${id}" method="post" modelAttribute="journal">

	<table>
	    <thead>
	    	<tr>
	            <td class="float-left">Journal Title:</td>
	            <td class="float-left">
	            	<form:errors path="journalTitle" class="text-danger"/>
					<form:input class="input" path="journalTitle"/>
	            </td>
	        </tr>
	        
	        <tr>
	            <td class="float-left">Entry:</td>
	            <td class="float-left">
	            	<form:errors path="entry" class="text-danger"/>
					<form:textarea path="entry" class="text-danger"/>
	            </td>
	        </tr>
	      	
	      	<tr>
	            <td class="float-left">Privacy:</td>
	            <td class="float-left">Public
	            	<form:errors path="privacy" class="text-danger"/>
					<form:radiobutton class="input" name="priv"  value= "0" path="privacy"/>
	            </td>
	            <td class="float-left">Private
	            	<form:errors path="privacy" class="text-danger"/>
					<form:radiobutton class="input" name="priv"  value= "1" path="privacy"/>
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