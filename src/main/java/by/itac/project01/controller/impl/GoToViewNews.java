package by.itac.project01.controller.impl;

import java.io.IOException;

import by.itac.project01.bean.News;
import by.itac.project01.controller.Atribute;
import by.itac.project01.controller.Command;
import by.itac.project01.controller.JSPPageName;
import by.itac.project01.controller.JSPParameter;
import by.itac.project01.controller.SessionAtribute;
import by.itac.project01.service.NewsService;
import by.itac.project01.service.ServiceException;
import by.itac.project01.service.ServiceProvider;
import by.itac.project01.service.validation.NewsValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GoToViewNews implements Command {

	private final NewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		News news;
		HttpSession session;
		int newsID;
		session = request.getSession(false);
		
		newsID = Util.takeNumber(request.getParameter(JSPParameter.JSP_ID_NEWS_PARAM));
						
		try {
			
			news = newsService.findById(newsID);
			request.setAttribute(Atribute.NEWS, news);
			request.setAttribute(Atribute.PRESENTATION, Atribute.VIEW_NEWS);
			
			session.setAttribute(SessionAtribute.PAGE_URL, Util.pageURL(JSPPageName.VIEW_NEWS,
					JSPParameter.JSP_ID_NEWS_PARAM, String.valueOf(newsID),
					Atribute.PRESENTATION, Atribute.VIEW_NEWS));

			request.getRequestDispatcher(JSPPageName.BASE_LAYOUT).forward(request, response);
		} catch (ServiceException | NewsValidationException e) {
			e.printStackTrace();
			response.sendRedirect(JSPPageName.GO_TO_ERROR_PAGE);
		}
	}
}
