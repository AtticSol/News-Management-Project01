package by.itac.project01.controller.impl;

import java.io.IOException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.itac.project01.controller.Command;
import by.itac.project01.service.ServiceException;
import by.itac.project01.service.ServiceProvider;
import by.itac.project01.service.UserService;
import by.itac.project01.service.validation.UserValidationException;
import by.itac.project01.util.JSPPageName;
import by.itac.project01.util.JSPParameter;
import by.itac.project01.util.SessionAtribute;
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
		
		login = request.getParameter(JSPParameter.JSP_LOGIN_PARAM);
		password = request.getParameter(JSPParameter.JSP_PASSWORD_PARAM);
				
		try {
			String role = userService.signIn(login, password);
			boolean guestRole = role.equals(SessionAtribute.ROLE_GUEST);
			
			if (!guestRole) {
				request.getSession(true).setAttribute(SessionAtribute.USER, SessionAtribute.USER_ACTIVE);
				request.getSession(true).setAttribute(SessionAtribute.ROLE, SessionAtribute.ROLE);
				response.sendRedirect(path(guestRole));
			} else {
				response.sendRedirect(path(guestRole));
			}
			
		} catch (ServiceException e) {
			log.log(Level.WARN, "", e);
			response.sendRedirect(JSPPageName.ERROR_PAGE);
		} catch (UserValidationException e) {
			response.sendRedirect(path(true));
		}
	}
	
	private String path(boolean guestRole) {
		if (guestRole) {
			return JSPPageName.AUTHENTICATION_ERROR_PAGE + SessionAtribute.SEPARATOR + 
					SessionAtribute.AUTHENTICATION_ERROR + SessionAtribute.EQUALS + SessionAtribute.AUTHENTICATION_ERROR_VALUE + SessionAtribute.SEPARATOR + 
					SessionAtribute.USER + SessionAtribute.EQUALS + SessionAtribute.USER_NOT_ACTIVE;
		}
		return JSPPageName.NEWS_LIST;
	}

}
