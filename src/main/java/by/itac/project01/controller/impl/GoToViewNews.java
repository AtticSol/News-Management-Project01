package by.itac.project01.controller.impl;

import java.io.IOException;

import by.itac.project01.bean.News;
import by.itac.project01.controller.Command;
import by.itac.project01.service.NewsService;
import by.itac.project01.service.ServiceException;
import by.itac.project01.service.ServiceProvider;
import by.itac.project01.util.JSPPageName;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToViewNews implements Command{
	
	private final NewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		News news;
		
		String id;
		
		id = request.getParameter("id");
		
		try {
			news = newsService.findById(Integer.parseInt(id));
			request.setAttribute("news", news);
			request.setAttribute("presentation", "viewNews");

			request.getRequestDispatcher(JSPPageName.BASE_LAYOUT).forward(request, response);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

}
