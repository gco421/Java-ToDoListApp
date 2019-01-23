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
    
    	<h1>Create a new idea</h1>
    	   <form:form method="post" action="/newideaprocess" modelAttribute="newidea" >
        <p>
        		<form:errors path="idea"/>
            <form:label path="idea">Content</form:label>
            <form:input path="idea"/>
        </p>
        <input type="hidden" path="user_id" value="${userId}"/>
        <input type="submit" value="Create"/>
    </form:form>  
</body>
</html>