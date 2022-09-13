package by.itac.project01.controller.impl;

import java.io.IOException;

import by.itac.project01.controller.Command;
import by.itac.project01.controller.SessionAtribute;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DoChangeLocal implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String local;
		String url;

		HttpSession session = request.getSession(false);

		local = request.getParameter(SessionAtribute.LOCAL);
		url = session.getAttribute(SessionAtribute.PAGE_URL).toString();
		session.setAttribute(SessionAtribute.LOCAL, local);

		response.sendRedirect(url);

	}
}
