package by.itac.project01.controller.impl;

import java.io.IOException;

import by.itac.project01.controller.Command;
import by.itac.project01.util.JSPPageName;
import by.itac.project01.util.SessionAtribute;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoSignOut implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getSession(true).setAttribute(SessionAtribute.USER, SessionAtribute.USER_NOT_ACTIVE);
		response.sendRedirect(JSPPageName.INDEX_JSP);
	}

}
