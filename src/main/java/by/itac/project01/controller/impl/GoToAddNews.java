package by.itac.project01.controller.impl;

import java.io.IOException;

import by.itac.project01.controller.Command;
import by.itac.project01.util.JSPPageName;
import by.itac.project01.util.Atribute;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToAddNews implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute(Atribute.PRESENTATION, Atribute.ADD_NEWS);
		request.getRequestDispatcher(JSPPageName.BASE_LAYOUT).forward(request, response);

	}

}
