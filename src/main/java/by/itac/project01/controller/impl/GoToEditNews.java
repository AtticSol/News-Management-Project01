package by.itac.project01.controller.impl;

import java.io.IOException;

import by.itac.project01.bean.News;
import by.itac.project01.controller.Atribute;
import by.itac.project01.controller.Command;
import by.itac.project01.controller.JSPPageName;
import by.itac.project01.controller.JSPParameter;
import by.itac.project01.controller.SessionAtribute;
import by.itac.project01.service.NewsService;
import by.itac.project01.service.ServiceException;
import by.itac.project01.service.ServiceProvider;
import by.itac.project01.service.validation.NewsValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GoToEditNews implements Command {
	private final NewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session;
		News news;
		int newsID;
		String prePresentation;
		int pageNumber;

		session = request.getSession(false);

		newsID = Util.takeNumber(request.getParameter(JSPParameter.JSP_ID_NEWS_PARAM));
		pageNumber = Util.takeNumber(request.getParameter(JSPParameter.JSP_PAGE_NUMBER_PARAM));
		prePresentation = request.getParameter(JSPParameter.JSP_PREVIOUS_PRESENTAION);

		try {
			news = newsService.findById(newsID);

			request.setAttribute(Atribute.PRESENTATION, Atribute.EDIT_NEWS);
			request.setAttribute(Atribute.NEWS, news);
			request.setAttribute(JSPParameter.JSP_PAGE_NUMBER_PARAM, pageNumber);
			request.setAttribute(JSPParameter.JSP_PREVIOUS_PRESENTAION, prePresentation);

			session.setAttribute(SessionAtribute.PAGE_URL,
					Util.pageURL(JSPPageName.GO_TO_EDIT_NEWS,
							JSPParameter.JSP_ID_NEWS_PARAM, String.valueOf(newsID),
							JSPParameter.JSP_PREVIOUS_PRESENTAION, prePresentation,
							JSPParameter.JSP_PAGE_NUMBER_PARAM, String.valueOf(pageNumber)));

			request.getRequestDispatcher(JSPPageName.BASE_LAYOUT).forward(request, response);
		} catch (ServiceException | NewsValidationException e) {
			e.printStackTrace();
			response.sendRedirect(JSPPageName.GO_TO_ERROR_PAGE);
		}

	}
}
