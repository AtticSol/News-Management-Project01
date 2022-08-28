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
import by.itac.project01.util.Atribute;
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
			int pageItem = pageItem(request);
			
			if (pageItem != 0) {
				newsList = newsService.newsListByPageNumber(pageItem, NewsParameter.MAX_NEWS_NUMBER_PER_PAGE);
				request.setAttribute(JSPParameter.JSP_PAGE_NUMBER_PARAM, pageItem);
			} else {
				newsList = newsService.latestList(NewsParameter.MAX_NEWS_NUMBER_PER_PAGE);
			}

			request.setAttribute(Atribute.NEWS, newsList);
			request.setAttribute(Atribute.PRESENTATION, Atribute.NEWS_LIST);
			request.setAttribute(Atribute.PAGE, pageList);

			request.getRequestDispatcher(JSPPageName.BASE_LAYOUT).forward(request, response);
		} catch (ServiceException e) {
			e.printStackTrace();
			response.sendRedirect(JSPPageName.ERROR_PAGE);
		}
	}

	private int pageItem(HttpServletRequest request) {
		String pageItem = request.getParameter(JSPParameter.JSP_PAGE_NUMBER_PARAM);
		if (pageItem != null) {
			return Integer.parseInt(pageItem);
		}
		return 0;
	}

}
