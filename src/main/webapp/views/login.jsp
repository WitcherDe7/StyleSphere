<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>Login</h3><br>
	<form action="login" method="Post">
	<% if(session.getAttribute("loginError") != null){ %>

			<h2><%= session.getAttribute("loginError") %></h2>
			<%session.removeAttribute("loginError");%>	

		<%} %>	
	<input type="text" placeholder="Enter email" name="email"/><br><br>
	<input type="password" placeholder="Enter password" name="password"/><br><br>
	<button name="login">Login</button>
	</form>
</body>
</html>