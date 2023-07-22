<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<title>Goal Details</title>
</head>
<body>

<h2><a href="/home">Back</a></h2>

<h1>View Goal</h1>
<table>
    <tbody>
    
    	<tr>
        	<td class="float-left">Goal Name:</td>
        	<td><c:out value="${goal.goalName}"></c:out></td>
        </tr>
        
        <tr>
        	<td class="float-left">Goal Length:</td>
            <td><c:out value="${goal.shortOrLong}"></c:out></td>
        </tr>
        
        <tr>
        	<td class="float-left">Description:</td>
            <td><c:out value="${goal.description}"></c:out></td>
        </tr>
        
    </tbody>
</table>

<c:if test = "${goal.aspirer.id==userId}">
	<h3><a href="/goal/edit/${goal.id}">Edit</a></h3>
    <h3><a href="/goal/delete/${goal.id}">Delete</a></h3>
</c:if>

</body>
</html>