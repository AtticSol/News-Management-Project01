package by.itac.project01.controller.impl;

import java.io.IOException;

import by.itac.project01.controller.Command;
import by.itac.project01.util.JSPPageName;
import by.itac.project01.util.JSPParameter;
import by.itac.project01.util.Atribute;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoCancel implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String presentaion;
		String pageItem;

		presentaion = request.getParameter(JSPParameter.PREVIOUS_PRESENTAION);
		pageItem = request.getParameter(JSPParameter.JSP_PAGE_NUMBER_PARAM);

		if (presentaion.equals(Atribute.VIEW_NEWS)) {
			request.getRequestDispatcher(JSPPageName.VIEW_NEWS).forward(request, response);
		} else {
			request.setAttribute(JSPParameter.JSP_PAGE_NUMBER_PARAM, pageItem);
			request.getRequestDispatcher(JSPPageName.NEWS_LIST).forward(request, response);
		}

	}

}
