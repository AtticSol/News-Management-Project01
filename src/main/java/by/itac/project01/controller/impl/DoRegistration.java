package by.itac.project01.controller.impl;

import java.io.IOException;
import java.util.Objects;

import by.itac.project01.bean.NewUserInfo;
import by.itac.project01.controller.Command;
import by.itac.project01.service.ServiceProvider;
import by.itac.project01.service.UserService;
import by.itac.project01.service.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoRegistration implements Command {

	private final UserService userService = ServiceProvider.getInstance().getUserService();

	private static final String JSP_NAME_PARAM = "name";
	private static final String JSP_LOGIN_PARAM = "login";
	private static final String JSP_PASSWORD_PARAM = "password";
	private static final String JSP_CONFIRM_PASSWORD_PARAM = "confirm_password";
	private static final String JSP_EMAIL_PARAM = "email";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		preValidation(request, response);
		
		try {
			NewUserInfo newUserInfo = userInfoFromRequest(request);

			if (userService.registration(newUserInfo)) {
				request.getSession(true).setAttribute("user", "active");
				request.getSession(true).setAttribute("role", "user");
				response.sendRedirect("controller?command=go_to_news_list");
			} else {
				request.getSession(true).setAttribute("user", "not_registered");
				request.setAttribute("RegistrationError", "wrong login or password");
				response.sendRedirect("controller?command=go_to_registration_page");
			}

		} catch (ServiceException e) {
			// logging e
			// go to error page
		}

	}

	private void preValidation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password;
		String confirmPassword;
		
		password = request.getParameter(JSP_PASSWORD_PARAM);
		confirmPassword = request.getParameter(JSP_CONFIRM_PASSWORD_PARAM);
		
		if(!password.equals(confirmPassword)) {
			request.getSession(true).setAttribute("user", "not_registered");
			request.setAttribute("RegistrationError", "wrong login or password");
			request.getRequestDispatcher("/WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
		}
	}

	private NewUserInfo userInfoFromRequest(HttpServletRequest request) {
		String name;
		String login;
		String password;
		String confirmPassword;
		String email;

		name = request.getParameter(JSP_NAME_PARAM);
		login = request.getParameter(JSP_LOGIN_PARAM);
		password = request.getParameter(JSP_PASSWORD_PARAM);
		confirmPassword = request.getParameter(JSP_CONFIRM_PASSWORD_PARAM);
		email = request.getParameter(JSP_EMAIL_PARAM);
		
		return new NewUserInfo(name, login, password, confirmPassword, email);
	}

}
