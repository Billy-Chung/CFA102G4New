package com.generalMember.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class GeneralMemberFilter implements Filter {
	private FilterConfig config;

	public void destroy() {
		config = null;
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		Object account = session.getAttribute("meb");
		
		if(account == null) {
			String location = req.getServletPath();		
			req.setAttribute("PK", req.getParameter("PK"));
			session.setAttribute("location",location);
			RequestDispatcher failureView = req.getRequestDispatcher("/front_end/GeneralMember/login.jsp");
			failureView.forward(req, res); 
			return;
		}else {
			chain.doFilter(req, res);
		}	
	}

	
	public void init(FilterConfig Config) throws ServletException {
		this.config = config;
	}

}
