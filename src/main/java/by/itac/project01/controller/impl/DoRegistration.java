package by.itac.project01.controller.impl;

import java.io.IOException;
import java.util.List;

import by.itac.project01.bean.NewUserInfo;
import by.itac.project01.controller.Command;
import by.itac.project01.service.ServiceException;
import by.itac.project01.service.ServiceProvider;
import by.itac.project01.service.UserService;
import by.itac.project01.service.validation.UserValidationException;
import by.itac.project01.util.InputDataError;
import by.itac.project01.util.JSPPageName;
import by.itac.project01.util.JSPParameter;
import by.itac.project01.util.Atribute;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoRegistration implements Command {

	private final UserService userService = ServiceProvider.getInstance().getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		boolean newUser = false;

		try {
			NewUserInfo newUserInfo = userInfoFromRequest(request);

			newUser = userService.registration(newUserInfo);
			if (newUser) {
				request.getSession(true).setAttribute(Atribute.USER, Atribute.USER_ACTIVE);
				request.getSession(true).setAttribute(Atribute.ROLE, Atribute.ROLE_USER);
				response.sendRedirect(path(newUser));
			} else {
				response.sendRedirect(path(newUser));
			}

		} catch (ServiceException e) {
			response.sendRedirect(path(false));
		} catch (UserValidationException e) {
			response.sendRedirect(path(false) + inputErrorList(e));
		}

	}

	private String inputErrorList(UserValidationException e) {
		String errors = "";
		List<InputDataError> errorList;

		errorList = e.getErrorList();

		for (InputDataError error : errorList) {
			errors = errors + Atribute.SEPARATOR + error.errorName() + Atribute.EQUALS + error.getTitle();
		}
		return errors;
	}

	private String path(boolean newUser) {
		if (!newUser) {

			StringBuffer sb = new StringBuffer();
			
			sb.append(JSPPageName.REGISTRATION_ERROR_PAGE);
			sb.append(Atribute.SEPARATOR);
			sb.append(Atribute.REGISTRATION_ERROR);
			sb.append(Atribute.EQUALS);
			sb.append(Atribute.REGISTRATION_ERROR_VALUE);
			sb.append(Atribute.SEPARATOR);
			sb.append(Atribute.USER);
			sb.append(Atribute.EQUALS);
			sb.append(Atribute.USER_NOT_REGISTERED);

			return sb.toString();
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
