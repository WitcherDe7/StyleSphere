<%@page import="java.sql.ResultSet"%>
<%@page import="com.stylesphere.database.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
<style>
</style>
<meta charset="ISO-8859-1">
<title>Users List</title>
</head>
<body>
	
	<h1>Users List</h1>
	
	<%
		UserDao uDao = new UserDao();
		ResultSet table = uDao.fetchRecord();
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
	   only for admin
	<% } else { %>
	   <h1>Access denied</h1>
	<% } %>
</body>
</html>
