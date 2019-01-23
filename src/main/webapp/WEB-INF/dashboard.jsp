<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Ideas</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
	<header>
	<a href="/logout">Logout</a>
	</header>
	<h1>Welcome, <c:out value="${user.name}"/></h1>
	<h1>Ideas</h1>
	<a href="">Low Likes</a>   <a href="">High Likes</a>
	<table style="widths:100%">
  <tr>
    <th>Idea</th>
    <th>Created By:</th> 
    <th>Likes</th> 
    <th>Action:</th> 
  </tr>
  	
	<c:forEach items="${ideas}" var="ideas">
	<tr> 
		<td><a href="/ideas/${ideas.id}"><c:out value="${ideas.idea}"/>
		</a>
		</td>
		<td>Elf</td>
		<td>10</td>
		<td><a href="">Like</a></td>
	</tr>
	</c:forEach> 
</table>
  <a href = "/ideas/new" type="button" class="btn btn-primary">Create an Idea</a>
    
</body>
</html>