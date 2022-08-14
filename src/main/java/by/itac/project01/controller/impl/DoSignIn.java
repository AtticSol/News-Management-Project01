package by.itac.project01.controller.impl;

import java.io.IOException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.itac.project01.controller.Command;
import by.itac.project01.service.ServiceProvider;
import by.itac.project01.service.UserService;
import by.itac.project01.service.exception.ServiceException;
import by.itac.project01.service.exception.UserValidationException;
import by.itac.project01.util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoSignIn implements Command{
	
	private final UserService userService = ServiceProvider.getInstance().getUserService();
	private static final Logger log = LogManager.getRootLogger();

		
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login;
		String password;
		
		login = request.getParameter(Constant.JSP_LOGIN_PARAM);
		password = request.getParameter(Constant.JSP_PASSWORD_PARAM);
				
		try {
			String role = userService.signIn(login, password);
			boolean guestRole = role.equals(Constant.ROLE_GUEST);
			
			if (!guestRole) {
				request.getSession(true).setAttribute(Constant.USER, Constant.USER_ACTIVE);
				request.getSession(true).setAttribute(Constant.ROLE, Constant.ROLE);
				response.sendRedirect(path(guestRole));
			} else {
				response.sendRedirect(path(guestRole));
			}
			
		} catch (ServiceException e) {
			log.log(Level.WARN, "", e);
			response.sendRedirect(Constant.ERROR_PAGE);
		} catch (UserValidationException e) {
			response.sendRedirect(path(true));
		}
	}
	
	private String path(boolean guestRole) {
		if (guestRole) {
			return Constant.AUTHENTICATION_ERROR_PAGE + Constant.SEPARATOR + 
					Constant.AUTHENTICATION_ERROR + Constant.EQUALS + Constant.AUTHENTICATION_ERROR_VALUE + Constant.SEPARATOR + 
					Constant.USER + Constant.EQUALS	+ Constant.USER_NOT_ACTIVE;
		}
		return Constant.NEWS_LIST;
	}

}
