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


@WebFilter("/logout")
public class LogoutFilter implements Filter {
    
    private String loginPage = "/views/login.jsp";
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // initialization code, if any
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        
        if (session != null && session.getAttribute("loggedInId") != null) {
            // perform any additional processing here
            
            // invalidate the session
            session.invalidate();
            
            // redirect to login page
            httpResponse.sendRedirect(httpRequest.getContextPath() + loginPage);
            return;
        }
        
        // continue the filter chain
        chain.doFilter(request, response);
    }
    
    @Override
    public void destroy() {
        // cleanup code, if any
    }
    
}
