package com.stylesphere.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/profile.jsp")
public class LoginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpSession session = ((HttpServletRequest)request).getSession();
		if(session.getAttribute("loggedInId") == null) {
			session.setAttribute("loginError","Do login First!");
			((HttpServletResponse)response).sendRedirect("login.jsp");
		}
		else {
			chain.doFilter(request, response);
		}
			 
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
}
