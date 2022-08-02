package by.itac.project01.controller.impl;

import java.io.IOException;

import by.itac.project01.controller.Command;
import by.itac.project01.service.ServiceProvider;
import by.itac.project01.service.UserService;
import by.itac.project01.service.exception.ServiceException;
import by.itac.project01.util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoSignIn implements Command{
	
	private final UserService userService = ServiceProvider.getInstance().getUserService();
		
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login;
		String password;
		
		login = request.getParameter(Constant.JSP_LOGIN_PARAM);
		password = request.getParameter(Constant.JSP_PASSWORD_PARAM);
		
		//short validation
		
		try {
			String role = userService.signIn(login, password);
			
			if (!role.equals("guest")) {
				request.getSession(true).setAttribute(Constant.USER, Constant.USER_ACTIVE);
				request.getSession(true).setAttribute(Constant.ROLE, Constant.ROLE);
				response.sendRedirect(path(true));
			} else {
				response.sendRedirect(path(false));

			}
			
		} catch (ServiceException e) {
			//logging e
			// go to error page
		}
	}
	
	private String path(boolean registered) {
		if (registered) {
			return Constant.NEWS_LIST;
		} else {
			return Constant.AUTHENTICATION_ERROR_PAGE + Constant.SEPARATOR + 
					Constant.AUTHENTICATION_ERROR + Constant.EQUALS + Constant.AUTHENTICATION_ERROR_VALUE + Constant.SEPARATOR + 
					Constant.USER + Constant.EQUALS	+ Constant.USER_NOT_ACTIVE;
		}
	}

}
