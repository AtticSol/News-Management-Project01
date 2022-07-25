package by.itac.project01.controller.impl;

import java.io.IOException;

import by.itac.project01.controller.Command;
import by.itac.project01.service.ServiceProvider;
import by.itac.project01.service.UserService;
import by.itac.project01.service.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoSignIn implements Command{
	
	private final UserService userService = ServiceProvider.getInstance().getUserService();
	
	private static final String JSP_LOGIN_PARAM = "login";
	private static final String JSP_PASSWORD_PARAM = "password";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login;
		String password;
		
		login = request.getParameter(JSP_LOGIN_PARAM);
		password = request.getParameter(JSP_PASSWORD_PARAM);
		
		//small validation
		
		try {
			String role = userService.signIn(login, password);
			
			if (!role.equals("guest")) {
				request.getSession(true).setAttribute("user", "active");
				request.getSession(true).setAttribute("role", role);
				response.sendRedirect("controller?command=go_to_news_list");
			} else {
				request.getSession(true).setAttribute("user", "not active");
				request.setAttribute("AuthenticationError", "wrong login or password");
				request.getRequestDispatcher("/WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
			}
			
		} catch (ServiceException e) {
			//logging e
			// go to error page
		}
	}

}
