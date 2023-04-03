<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>Registration</h3><br>
	<form action="register" method="Post">
	<% if(session.getAttribute("registerErr") != null){ %>

			<h2><%= session.getAttribute("registerErr") %></h2>
			<%session.removeAttribute("registerErr");%>	

		<%} %>	
	<input type="text" placeholder="Enter name" name="name" /><br><br>
	<input type="text" placeholder="Enter email" name="email"/><br><br>
	<input type="password" placeholder="Enter password" name="password"/><br><br>
	<button name="register">Register</button>
	</form>
</body>
</html>