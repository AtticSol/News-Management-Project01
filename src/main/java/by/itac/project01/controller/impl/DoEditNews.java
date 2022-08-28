package by.itac.project01.controller.impl;

import java.io.IOException;
import java.time.LocalDate;

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

public class DoEditNews implements Command {
	private final NewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		News news;
		int idNews;

		idNews = Integer.parseInt(request.getParameter(JSPParameter.JSP_ID_NEWS_PARAM));
		news = newsInfoFromRequest(request);
		news.setIdNews(idNews);

		try {
			newsService.updateNews(news);
			request.getSession(true).setAttribute(Atribute.NEWS, news);
			response.sendRedirect(path(idNews, news));
		} catch (ServiceException e) {
			e.printStackTrace();
			response.sendRedirect(JSPPageName.ERROR_PAGE);
		}
	}

	private News newsInfoFromRequest(HttpServletRequest request) {
		String title;
		String briefNews;
		String content;
		LocalDate newsDate;
		
		title = request.getParameter(NewsParameter.TITLE_COLUMN);
		briefNews = request.getParameter(NewsParameter.BRIEF_COLUMN);
		content = request.getParameter(NewsParameter.CONTENT_COLUMN);
		String date;
		date = request.getParameter(NewsParameter.DATE_COLUMN);
		newsDate = LocalDate.parse(date);

		return new News(title, briefNews, content, newsDate);
	}

	private String path(int idNews, News news) {
		StringBuffer sb = new StringBuffer();
		sb.append(JSPPageName.VIEW_NEWS);
		sb.append(Atribute.SEPARATOR);
		sb.append(Atribute.PRESENTATION);
		sb.append(Atribute.EQUALS);
		sb.append(Atribute.VIEW_NEWS);
		sb.append(Atribute.SEPARATOR);
		sb.append(Atribute.NEWS_ID);
		sb.append(Atribute.EQUALS);
		sb.append(idNews);

		return  sb.toString();
		}
}
