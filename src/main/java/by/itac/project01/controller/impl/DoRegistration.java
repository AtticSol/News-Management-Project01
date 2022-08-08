package by.itac.project01.controller.impl;

import java.io.IOException;
import java.util.List;

import by.itac.project01.bean.NewUserInfo;
import by.itac.project01.controller.Command;
import by.itac.project01.service.ServiceProvider;
import by.itac.project01.service.UserService;
import by.itac.project01.service.exception.ServiceException;
import by.itac.project01.service.exception.UserValidationException;
import by.itac.project01.util.Constant;
import by.itac.project01.util.InputDataError;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoRegistration implements Command {

	private final UserService userService = ServiceProvider.getInstance().getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		boolean registered = false;

		try {
			NewUserInfo newUserInfo = userInfoFromRequest(request);

			registered = userService.registration(newUserInfo);
			if (registered) {
				request.getSession(true).setAttribute(Constant.USER, Constant.USER_ACTIVE);
				request.getSession(true).setAttribute(Constant.ROLE, Constant.ROLE_USER);
				response.sendRedirect(path(registered, ""));
			} else {
				response.sendRedirect(path(registered, ""));
			}

		} catch (ServiceException e) {
			response.sendRedirect(path(false, ""));
		} catch (UserValidationException e) {
			response.sendRedirect(path(false, inputErrorList(e)));
		}

	}

	private String inputErrorList(UserValidationException e) {
		String errors = "";
		List<InputDataError> errorList;
		
		errorList = e.getErrorList();
		
		for (InputDataError error : errorList) {
			errors = errors + Constant.SEPARATOR + error.errorName() + Constant.EQUALS + error.getTitle();
		}
		return errors;
	}


	private String path(boolean registered, String str) {
		if (registered) {
			return Constant.NEWS_LIST;
		} else {
			return Constant.REGISTRATION_ERROR_PAGE + Constant.SEPARATOR
					+ Constant.REGISTRATION_ERROR + Constant.EQUALS	+ Constant.REGISTRATION_ERROR_VALUE + Constant.SEPARATOR
					+ Constant.USER + Constant.EQUALS + Constant.USER_NOT_REGISTERED + str;
		}
	}

	private NewUserInfo userInfoFromRequest(HttpServletRequest request) {
		String name;
		String login;
		String password;
		String confirmPassword;
		String email;

		name = request.getParameter(Constant.JSP_NAME_PARAM);
		login = request.getParameter(Constant.JSP_LOGIN_PARAM);
		password = request.getParameter(Constant.JSP_PASSWORD_PARAM);
		confirmPassword = request.getParameter(Constant.JSP_CONFIRM_PASSWORD_PARAM);
		email = request.getParameter(Constant.JSP_EMAIL_PARAM);

		return new NewUserInfo(name, login, password, confirmPassword, email);
	}

}
