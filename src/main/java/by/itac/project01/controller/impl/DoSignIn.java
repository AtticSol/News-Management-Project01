package by.itac.project01.controller.impl;

import java.io.IOException;

import by.itac.project01.controller.Command;
import by.itac.project01.service.ServiceException;
import by.itac.project01.service.ServiceProvider;
import by.itac.project01.service.UserService;
import by.itac.project01.service.validation.UserValidationException;
import by.itac.project01.util.JSPPageName;
import by.itac.project01.util.JSPParameter;
import by.itac.project01.util.Atribute;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoSignIn implements Command{
	
	private final UserService userService = ServiceProvider.getInstance().getUserService();
		
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login;
		String password;
		
		login = request.getParameter(JSPParameter.JSP_LOGIN_PARAM);
		password = request.getParameter(JSPParameter.JSP_PASSWORD_PARAM);
				
		try {
			int idUser = userService.userID(login, password);
			String role = userService.getRole(idUser);
			boolean guestRole = role.equals(Atribute.ROLE_GUEST);
			
			if (!guestRole) {
				request.getSession(true).setAttribute(Atribute.USER, Atribute.USER_ACTIVE);
				request.getSession(true).setAttribute(Atribute.ROLE, role);
				request.getSession(true).setAttribute(Atribute.USER_ID, idUser);
				response.sendRedirect(path(guestRole));
			} else {
				response.sendRedirect(path(guestRole));
			}
			
		} catch (ServiceException e) {
			response.sendRedirect(JSPPageName.ERROR_PAGE);
		} catch (UserValidationException e) {
			response.sendRedirect(path(true));
		}
	}
	
	private String path(boolean guestRole) {
		if (!guestRole) {
			return JSPPageName.NEWS_LIST;
		}
		return JSPPageName.AUTHENTICATION_ERROR_PAGE + Atribute.SEPARATOR + 
					Atribute.AUTHENTICATION_ERROR + Atribute.EQUALS + Atribute.AUTHENTICATION_ERROR_VALUE + Atribute.SEPARATOR + 
					Atribute.USER + Atribute.EQUALS + Atribute.USER_NOT_ACTIVE;
	}

}
