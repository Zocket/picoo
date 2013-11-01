package com.picoo.tutorial;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.*;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import java.util.logging.Logger;

public class AuthenticationFilter implements Filter {
	private FilterConfig filterConfig;
	private static final Logger log=Logger.getLogger(AuthenticationFilter.class.getName());
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain fc) throws IOException, ServletException {
		// TODO Auto-generated method stub
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		
		log.info("Request URI: "+((HttpServletRequest)req).getRequestURI());

		if (user != null) {
			fc.doFilter(req, resp);
		} else {
			((HttpServletResponse)resp).sendRedirect(userService.createLoginURL(((HttpServletRequest)req).getRequestURI()));
		}

	}

	@Override
	public void init(FilterConfig fc) throws ServletException {
		// TODO Auto-generated method stub
		this.filterConfig=fc;

	}
	
	public FilterConfig getFilterConfig(){
		return filterConfig;
	}

}
