package by.itac.project01.controller.impl;

import java.io.IOException;

import by.itac.project01.controller.Atribute;
import by.itac.project01.controller.Command;
import by.itac.project01.controller.JSPPageName;
import by.itac.project01.controller.JSPParameter;
import by.itac.project01.controller.SessionAtribute;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GoToAddNews implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session;
		String prePresentation;
		int pageNumber;
		
		session = request.getSession(false);
		prePresentation = request.getParameter(JSPParameter.JSP_PREVIOUS_PRESENTAION);
		
		pageNumber = Util.takeNumber(request.getParameter(JSPParameter.JSP_PAGE_NUMBER_PARAM));

		session.setAttribute(SessionAtribute.PAGE_URL, Util.pageURL(JSPPageName.GO_TO_ADD_NEWS,
				JSPParameter.JSP_PREVIOUS_PRESENTAION, prePresentation,
				JSPParameter.JSP_PAGE_NUMBER_PARAM, String.valueOf(pageNumber)));
		
		request.setAttribute(JSPParameter.JSP_PAGE_NUMBER_PARAM, pageNumber);
		request.setAttribute(JSPParameter.JSP_PREVIOUS_PRESENTAION, prePresentation);
		request.setAttribute(Atribute.PRESENTATION, Atribute.ADD_NEWS);
		request.getRequestDispatcher(JSPPageName.BASE_LAYOUT).forward(request, response);

	}

}
