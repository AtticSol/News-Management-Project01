package by.itac.project01.controller.impl;

import java.io.IOException;

import by.itac.project01.controller.Command;
import by.itac.project01.controller.JSPPageName;
import by.itac.project01.controller.SessionAtribute;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GoToRegistrationPage implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session;
		
		session = request.getSession(false);
		
		session.setAttribute(SessionAtribute.USER_STATUS, SessionAtribute.REGISTERATION);
		session.setAttribute(SessionAtribute.PAGE_URL, JSPPageName.GO_TO_REGISTRATION_PAGE);
		request.getRequestDispatcher(JSPPageName.BASE_LAYOUT).forward(request, response);
	}
}
