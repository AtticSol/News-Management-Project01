package by.itac.project01.controller.impl;

import java.io.IOException;

import by.itac.project01.bean.News;
import by.itac.project01.controller.Command;
import by.itac.project01.service.NewsService;
import by.itac.project01.service.ServiceException;
import by.itac.project01.service.ServiceProvider;
import by.itac.project01.util.JSPPageName;
import by.itac.project01.util.JSPParameter;
import by.itac.project01.util.SessionAtribute;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToViewNews implements Command{
	
	private final NewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		News news;
		
		String id;
		
		id = request.getParameter(JSPParameter.JSP_ID_PARAM);
		
		try {
			news = newsService.findById(Integer.parseInt(id));
			request.setAttribute(SessionAtribute.NEWS, news);
			request.setAttribute(SessionAtribute.PRESENTATION, SessionAtribute.VIEW_NEWS);
			
			request.getRequestDispatcher(JSPPageName.BASE_LAYOUT).forward(request, response);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

}
