package by.itac.project01.controller.impl;

import java.io.IOException;
import java.util.List;

import by.itac.project01.bean.NewUserInfo;
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
import by.itac.project01.util.InputUserDataError;
import by.itac.project01.util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DoRegistration implements Command {

	private final UserService userService = ServiceProvider.getInstance().getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session;
		int userID;
		String role;
		
		role = Role.GUEST.getTitle();

		try {
			NewUserInfo newUserInfo = userInfoFromRequest(request);
			userID = userService.registration(newUserInfo);
	
			session = request.getSession(true);
			
			if (userID != Constant.NO_NUMBER) {	
				role = Role.USER.getTitle();
				
				session.setAttribute(SessionAtribute.USER_STATUS, SessionAtribute.ACTIVE);
				session.setAttribute(Role.ROLE.getTitle(), role);
				session.setAttribute(SessionAtribute.USER_ID, userID);

				response.sendRedirect(path(role));
				
			} else {
				response.sendRedirect(path(role));
			}

		} catch (ServiceException e) {
			response.sendRedirect(path(role));
		} catch (UserValidationException e) {
			response.sendRedirect(path(role) + inputErrorList(e));
		}

	}

	private String inputErrorList(UserValidationException e) {
		String errors = "";
		List<InputUserDataError> errorList;

		errorList = e.getErrorList();

		for (InputUserDataError error : errorList) {
			errors = errors + Atribute.SEPARATOR + error.getTitle() + Atribute.EQUALS + error.getTitle();
		}
		return errors;
	}

	private String path(String role) {
		if (role == Role.GUEST.getTitle()) {
			return Util.pageURL(JSPPageName.REGISTRATION_ERROR_PAGE,
					Atribute.REGISTRATION_ERROR, Atribute.REGISTRATION_ERROR);
		}
		
		return JSPPageName.NEWS_LIST;
	}

	private NewUserInfo userInfoFromRequest(HttpServletRequest request) {
		String name;
		String login;
		String password;
		String confirmPassword;
		String email;

		name = request.getParameter(JSPParameter.JSP_NAME_PARAM);
		login = request.getParameter(JSPParameter.JSP_LOGIN_PARAM);
		password = request.getParameter(JSPParameter.JSP_PASSWORD_PARAM);
		confirmPassword = request.getParameter(JSPParameter.JSP_CONFIRM_PASSWORD_PARAM);
		email = request.getParameter(JSPParameter.JSP_EMAIL_PARAM);

		return new NewUserInfo(name, login, password, confirmPassword, email);
	}

}
