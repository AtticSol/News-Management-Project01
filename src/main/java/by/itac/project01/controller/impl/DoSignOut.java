package by.itac.project01.controller.impl;

import java.io.IOException;

import by.itac.project01.controller.Command;
import by.itac.project01.controller.JSPPageName;
import by.itac.project01.controller.Role;
import by.itac.project01.controller.SessionAtribute;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DoSignOut implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session;
		
		session = request.getSession(false);
		
		session.removeAttribute(SessionAtribute.USER_STATUS);
		session.removeAttribute(Role.ROLE.getTitle());
		session.removeAttribute(SessionAtribute.USER_ID);
		response.sendRedirect(JSPPageName.INDEX_JSP);
	}

}
