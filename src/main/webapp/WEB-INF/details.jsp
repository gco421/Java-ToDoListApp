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

<h1><c:out value="${idea.idea}"/></h1>
	<p>Created By: Elf</p>
	<h1>Users who liked your idea</h1>
	<table style="widths:100%">
  <tr>
    <th>Name</th>
  </tr>
  	
	<%-- <c:forEach items="" var=""> --%>
	<tr> 
		<td>Reed Hastings
		</td>
	</tr>
<%-- 	</c:forEach> --%>
</table>
  <a href = "/ideas/${idea.id}/edit" type="button" class="btn btn-primary">Edit</a>
    
</body>
</html>