<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login and Registration</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
	<container>
	<div>
	<p><c:out value="${error}"/></p>
	</div>
	<div class="col-md-6">
	<p><form:errors path="user.*"/></p>
    <h1>Register!</h1>
    
    <form:form method="POST" action="/registration" modelAttribute="user">
     	<p>
            <form:label path="name">Name:</form:label>
            <form:input type="text" path="name"/>
        </p>
        <p>
            <form:label path="email">Email</form:label>
            <form:input type="email" path="email"/>
        </p>
        <p>
            <form:label path="password">Password</form:label>
            <form:password path="password"/>
        </p>
        <p>
            <form:label path="passwordConfirmation">Password Confirmation</form:label>
            <form:password path="passwordConfirmation"/>
        </p>
        <input type="submit" value="Register!"/>
    </form:form>
    </div>
    <br>
	<div class="col-md-6">
    <h1>Login</h1>
    
    <form method="post" action="/login" modelAttribute="user">
        <p>
            <label type="email" for="email">Email</label>
            <input type="text" id="email" name="email"/>
        </p>
        <p>
            <label for="password">Password</label>
            <input type="password" id="password" name="password"/>
        </p>
        <input type="submit" value="Login!"/>
    </form>  
    </div> 
    </container> 
</body>
</html>