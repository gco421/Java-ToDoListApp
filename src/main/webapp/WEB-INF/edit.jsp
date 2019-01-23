<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ideas</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

</head>
<body>
		<form:errors path="idea"/>
		<h1>Edit <c:out value="${idea.idea}"/></h1>
  		<form:form action="/ideas/${idea.id}/edit" method="PUT" modelAttribute="idea">
            <form:label path="idea">Content</form:label>
            <form:input value ="${idea.idea}" path="idea"/>
        <input type="submit" value="Update"/>
		</form:form> 
    	<a href = "/ideas/delete/${idea.id}" type="button" class="btn btn-primary">Delete</a>
</body>
</html>