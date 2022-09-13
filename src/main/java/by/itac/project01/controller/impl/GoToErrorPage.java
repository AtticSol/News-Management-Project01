package by.itac.project01.controller.impl;

import java.io.IOException;

import by.itac.project01.controller.Command;
import by.itac.project01.controller.JSPPageName;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToErrorPage implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(JSPPageName.ERROR_PAGE).forward(request, response);
	}

}
