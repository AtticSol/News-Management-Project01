package by.itac.project01.controller.impl;

import java.io.IOException;

import by.itac.project01.controller.Atribute;
import by.itac.project01.controller.Command;
import by.itac.project01.controller.JSPPageName;
import by.itac.project01.controller.JSPParameter;
import by.itac.project01.controller.Role;
import by.itac.project01.controller.SessionAtribute;
import by.itac.project01.service.ServiceException;
import by.itac.project01.service.ServiceProvider;
import by.itac.project01.service.UserService;
import by.itac.project01.service.validation.UserValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DoSignIn implements Command{
	
	private final UserService userService = ServiceProvider.getInstance().getUserService();
		
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login;
		String password;
		HttpSession session;
		
		login = request.getParameter(JSPParameter.JSP_LOGIN_PARAM);
		password = request.getParameter(JSPParameter.JSP_PASSWORD_PARAM);
				
		try {
			int idUser = userService.userID(login, password);
			String role = userService.role(idUser);
			boolean guestRole = role.equals(Role.GUEST.getTitle());
			
			session = request.getSession(true);
			
			if (!guestRole) {
				session.setAttribute(SessionAtribute.USER_STATUS, SessionAtribute.ACTIVE);
				session.setAttribute(Role.ROLE.getTitle(), role);
				session.setAttribute(SessionAtribute.USER_ID, idUser);
				session.setAttribute(SessionAtribute.PAGE_URL, JSPPageName.NEWS_LIST);

				response.sendRedirect(path(guestRole));
			} else {
				response.sendRedirect(path(guestRole));
			}
			
		} catch (ServiceException e) {
			response.sendRedirect(JSPPageName.GO_TO_ERROR_PAGE);
		} catch (UserValidationException e) {
			response.sendRedirect(path(true));
		}
	}
	
	private String path(boolean guestRole) {
		if (!guestRole) {
			return JSPPageName.NEWS_LIST;
		}
		return Util.pageURL(JSPPageName.AUTHENTICATION_ERROR_PAGE,
					Atribute.AUTHENTICATION_ERROR, Atribute.AUTHENTICATION_ERROR);
	}

}
