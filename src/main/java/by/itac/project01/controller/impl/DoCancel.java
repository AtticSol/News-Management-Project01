package by.itac.project01.controller.impl;

import java.io.IOException;

import by.itac.project01.controller.Atribute;
import by.itac.project01.controller.Command;
import by.itac.project01.controller.JSPPageName;
import by.itac.project01.controller.JSPParameter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoCancel implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String prePresentaion;
		int pageNumber;

		prePresentaion = request.getParameter(JSPParameter.JSP_PREVIOUS_PRESENTAION);
		
		if (Atribute.VIEW_NEWS.equals(prePresentaion)) {
			request.getRequestDispatcher(JSPPageName.VIEW_NEWS).forward(request, response);
		} else {
			
			pageNumber = Util.takeNumber(request.getParameter(JSPParameter.JSP_PAGE_NUMBER_PARAM));
			request.setAttribute(JSPParameter.JSP_PAGE_NUMBER_PARAM, pageNumber);
			request.getRequestDispatcher(JSPPageName.NEWS_LIST).forward(request, response);
		}

	}

}
