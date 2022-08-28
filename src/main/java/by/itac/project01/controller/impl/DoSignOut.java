package by.itac.project01.controller.impl;

import java.io.IOException;

import by.itac.project01.controller.Command;
import by.itac.project01.util.JSPPageName;
import by.itac.project01.util.Atribute;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DoSignOut implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		
		session.setAttribute(Atribute.USER, Atribute.USER_NOT_ACTIVE);
		session.removeAttribute(Atribute.ROLE);
		session.removeAttribute(Atribute.USER_ID);
		response.sendRedirect(JSPPageName.INDEX_JSP);
	}

}
