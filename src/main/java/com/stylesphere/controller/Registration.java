package com.stylesphere.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.stylesphere.model.*;


@SuppressWarnings("serial")
@WebServlet("/views/register")
@MultipartConfig
public class Registration extends HttpServlet {
	public void service(HttpServletRequest request,HttpServletResponse response) throws  IOException, ServletException {
		
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Part userImage = request.getPart("image");
		String path = getServletContext().getInitParameter("imagePath");
		userImage.write(path+"userimage/_"+name+".png");
		

		
		UserDao uDao = new UserDao();
		String message = uDao.registration(name, email, password);
		
		PrintWriter out = response.getWriter();
 		out.println("<h1>"+message+"</h1>");
 		
 		response.setContentType("text/html");
 		
		
	}
}