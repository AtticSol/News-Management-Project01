package by.itac.project01.controller.impl;

import java.io.IOException;
import java.sql.Timestamp;

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

public class AddNews implements Command {
	private final NewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		News news;

		try {
			news = newsInfoFromRequest(request);
			newsService.save(news);

				request.setAttribute(SessionAtribute.NEWS, news);
				request.setAttribute(SessionAtribute.PRESENTATION, SessionAtribute.VIEW_NEWS);
				response.sendRedirect(JSPPageName.VIEW_NEWS);


		} catch (ServiceException e) {
			e.printStackTrace();
			response.sendRedirect(JSPPageName.ERROR_PAGE);
		}

	}
	
	private News newsInfoFromRequest(HttpServletRequest request) {
		String title;
		String briefNews;
		String content;
		Timestamp newsDate;
		
		
		title = request.getParameter(NewsParameter.TITLE_COLUMN);
		briefNews = request.getParameter(NewsParameter.BRIEF_COLUMN);
		content = request.getParameter(NewsParameter.CONTENT_COLUMN);
		newsDate = new Timestamp(System.currentTimeMillis());
		
		
		return new News(title, briefNews, content, newsDate);
	}

}
