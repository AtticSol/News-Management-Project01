package by.itac.project01.controller.impl;

import java.io.IOException;
import java.util.List;

import by.itac.project01.bean.News;
import by.itac.project01.controller.Command;
import by.itac.project01.service.NewsService;
import by.itac.project01.service.ServiceException;
import by.itac.project01.service.ServiceProvider;
import by.itac.project01.util.JSPPageName;
import by.itac.project01.util.NewsParameter;
import by.itac.project01.util.SessionAtribute;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToBasePage implements Command{
	
	private final NewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<News> latestNews;
		try {
			latestNews = newsService.latestList(NewsParameter.MAX_NEWS_NUMBER_PER_PAGE);
			request.setAttribute(SessionAtribute.NEWS, latestNews);
			//request.setAttribute("news", null);
			
			request.getRequestDispatcher(JSPPageName.BASE_LAYOUT).forward(request, response);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

}
