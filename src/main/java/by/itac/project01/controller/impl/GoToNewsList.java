package by.itac.project01.controller.impl;

import java.io.IOException;
import java.util.List;

import by.itac.project01.bean.News;
import by.itac.project01.controller.Command;
import by.itac.project01.service.NewsService;
import by.itac.project01.service.ServiceException;
import by.itac.project01.service.ServiceProvider;
import by.itac.project01.util.JSPPageName;
import by.itac.project01.util.JSPParameter;
import by.itac.project01.util.NewsParameter;
import by.itac.project01.util.SessionAtribute;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToNewsList implements Command {

	private final NewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<News> newsList;
		List<Integer> pageList;
		try {
			pageList = newsService.pageList();
			
			String pageItem = request.getParameter(JSPParameter.JSP_PAGE_NUMBER_PARAM);
			if (pageItem != null) {
				int intPageNumber = Integer.parseInt(pageItem);
				newsList = newsService.newsListByPageNumber(intPageNumber, NewsParameter.MAX_NEWS_NUMBER_PER_PAGE);
			} else {
				newsList = newsService.latestList(NewsParameter.MAX_NEWS_NUMBER_PER_PAGE);

			}
			
			request.setAttribute(SessionAtribute.NEWS, newsList);
			request.setAttribute(SessionAtribute.PRESENTATION, SessionAtribute.NEWS_LIST);
			request.setAttribute(SessionAtribute.PAGE, pageList);

			request.getRequestDispatcher(JSPPageName.BASE_LAYOUT).forward(request, response);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

}
