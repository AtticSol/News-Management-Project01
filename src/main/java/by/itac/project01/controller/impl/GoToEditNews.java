package by.itac.project01.controller.impl;

import java.io.IOException;

import by.itac.project01.bean.News;
import by.itac.project01.controller.Command;
import by.itac.project01.service.NewsService;
import by.itac.project01.service.ServiceException;
import by.itac.project01.service.ServiceProvider;
import by.itac.project01.util.JSPPageName;
import by.itac.project01.util.JSPParameter;
import by.itac.project01.util.Atribute;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToEditNews implements Command {
	private final NewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		News news;
		String idNews;
		String prePresentation;
		String pageItem;
		
		idNews = request.getParameter(JSPParameter.JSP_ID_NEWS_PARAM);
		prePresentation = request.getParameter(JSPParameter.PREVIOUS_PRESENTAION);
		pageItem = request.getParameter(JSPParameter.JSP_PAGE_NUMBER_PARAM);
		
		try {
			news = newsService.findById(Integer.parseInt(idNews));
			request.setAttribute(Atribute.PRESENTATION, Atribute.EDIT_NEWS);
			request.setAttribute(Atribute.NEWS, news);
			request.setAttribute(JSPParameter.JSP_PAGE_NUMBER_PARAM, pageItem);
			request.setAttribute(JSPParameter.PREVIOUS_PRESENTAION, prePresentation);
			
			request.getRequestDispatcher(JSPPageName.BASE_LAYOUT).forward(request, response);
		} catch (ServiceException e) {
			e.printStackTrace();
			response.sendRedirect(JSPPageName.ERROR_PAGE);
		}

	}

}
