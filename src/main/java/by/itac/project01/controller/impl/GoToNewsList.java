package by.itac.project01.controller.impl;

import java.io.IOException;
import java.util.List;

import by.itac.project01.bean.News;
import by.itac.project01.controller.Command;
import by.itac.project01.service.NewsService;
import by.itac.project01.service.ServiceException;
import by.itac.project01.service.ServiceProvider;
import by.itac.project01.util.JSPPageName;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToNewsList implements Command{
	
	private final NewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<News> newsList;
		try {
			newsList = newsService.list();
			request.setAttribute("news", newsList);
			request.setAttribute("presentation", "newsList");
			
			request.getRequestDispatcher(JSPPageName.BASE_LAYOUT).forward(request, response);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

}
