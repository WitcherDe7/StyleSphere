<%@page import="java.sql.ResultSet"%>
<%@page import="com.stylesphere.model.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%
    boolean isLoggedIn = (session != null && session.getAttribute("loggedInId") != null);
    
    if (!isLoggedIn) {
        // user is not logged in, redirect to login page
        response.sendRedirect(request.getContextPath() + "/views/login.jsp");
    }
%>
<!DOCTYPE html>
<html>
<head>
<style>
</style>
<meta charset="ISO-8859-1">
<title>Users List</title>
</head>
<body>
	<%
		String email = (String) session.getAttribute("loggedInId");
	%>
	<p>Your email is: <%= email %></p>
<h1>This is Profile.</h1>
<form id="logout-form" method="post" action="${pageContext.request.contextPath}/logout">
    <input type="hidden" name="logout" value="true" />
</form>

<button type="button" onclick="document.getElementById('logout-form').submit();">Logout</button>
	<h1>Users List</h1>
	
	<%
		UserDao uDao = new UserDao();
		ResultSet table = uDao.fetchUserRecord(email);

		if(table != null){
	%>	
			<table border="1">
			 	<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Role</th>
					
				</tr>
				<%
					while(table.next()){
						String id = table.getString("user_id");
						String name = table.getString("username");
						int role = table.getInt("is_admin");
						String roleStr = (role == 0) ? "user" : "admin";
						session = request.getSession();
						session.setAttribute("role", role);
				%>		
						<tr>
							<td><%= id %></td>
							<td><%= name %></td>
							<td><%= roleStr %></td>
						</tr>		
				<%
					}
				%>
			</table> 
	<%		
		}	
	%>
	
		<% Integer role = (Integer) session.getAttribute("role");
		   if (role != null && role == 1) { %>
		   you are admin
		<% } else { %>
		   <h1>You are not admin</h1>
		<% } %>
	
</body>
</html>
