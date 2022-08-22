package by.itac.project01.controller.impl;

import java.io.IOException;

import by.itac.project01.controller.Command;
import by.itac.project01.util.JSPPageName;
import by.itac.project01.util.SessionAtribute;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DoSignOut implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		
		session.setAttribute(SessionAtribute.USER, SessionAtribute.USER_NOT_ACTIVE);
		session.removeAttribute(SessionAtribute.AUTHENTICATION_ERROR);
		session.removeAttribute(SessionAtribute.ROLE);
		session.removeAttribute(SessionAtribute.USER_ID);
		response.sendRedirect(JSPPageName.INDEX_JSP);
	}

}
