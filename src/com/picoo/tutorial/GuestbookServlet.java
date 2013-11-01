package com.picoo.tutorial;

import java.io.IOException;

import javax.servlet.http.*;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class GuestbookServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7620657004606627844L;

	@Override
	public void doGet(HttpServletRequest req,HttpServletResponse resp)
	throws IOException{
		resp.setContentType("text/plain");
		resp.getWriter().print("Hello, world ");
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		resp.getWriter().println(user.getNickname());
		resp.getWriter().println(user.getUserId());
	}
}
