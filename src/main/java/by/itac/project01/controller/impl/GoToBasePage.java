package by.itac.project01.controller.impl;

import java.io.IOException;
import java.util.List;

import by.itac.project01.bean.News;
import by.itac.project01.controller.Atribute;
import by.itac.project01.controller.Command;
import by.itac.project01.controller.JSPPageName;
import by.itac.project01.controller.SessionAtribute;
import by.itac.project01.service.NewsService;
import by.itac.project01.service.ServiceException;
import by.itac.project01.service.ServiceProvider;
import by.itac.project01.service.validation.NewsValidationException;
import by.itac.project01.util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GoToBasePage implements Command {

	private final NewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<News> latestNews;
		HttpSession session;

		try {
			latestNews = newsService.latestList(Constant.MAX_NEWS_NUMBER_PER_PAGE);

			session = request.getSession(true);

			session.setAttribute(SessionAtribute.PAGE_URL, JSPPageName.GO_TO_BASE_PAGE);
			request.setAttribute(Atribute.NEWS, latestNews);

			request.getRequestDispatcher(JSPPageName.BASE_LAYOUT).forward(request, response);
		} catch (ServiceException | NewsValidationException e) {
			e.printStackTrace();
			request.getRequestDispatcher(JSPPageName.GO_TO_ERROR_PAGE).forward(request, response);
		}
	}

}
