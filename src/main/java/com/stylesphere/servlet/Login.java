package com.stylesphere.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.stylesphere.database.*;

@WebServlet("/views/login")
public class Login extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UserDao uDao = new UserDao();
		boolean isValid =  uDao.checkLogin(email,password);
		HttpSession session = request.getSession();
		if(isValid == true) {
			session.setAttribute("loggedInId",email);
			response.sendRedirect("profile.jsp");
		}
		else {
			session.setAttribute("loginError","Invalid id or password");
			response.sendRedirect("login.jsp");		
		}
		
	}
}