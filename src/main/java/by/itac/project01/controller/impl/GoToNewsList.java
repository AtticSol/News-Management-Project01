package by.itac.project01.controller.impl;

import java.io.IOException;
import java.util.List;

import by.itac.project01.bean.News;
import by.itac.project01.controller.Atribute;
import by.itac.project01.controller.Command;
import by.itac.project01.controller.JSPPageName;
import by.itac.project01.controller.JSPParameter;
import by.itac.project01.controller.Role;
import by.itac.project01.controller.SessionAtribute;
import by.itac.project01.service.NewsService;
import by.itac.project01.service.ServiceException;
import by.itac.project01.service.ServiceProvider;
import by.itac.project01.service.validation.NewsValidationException;
import by.itac.project01.util.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GoToNewsList implements Command {

	private final NewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session;
		List<News> newsList;
		List<Integer> pageList;
		String guestRole;
		
		session = request.getSession(false);
		guestRole = (String) session.getAttribute(Role.ROLE.getTitle());

		try {
			pageList = newsService.pageList();			
			int pageNumber = takePageNumber(request);
			
			if (!Role.GUEST.getTitle().equals(guestRole)) {
				newsList = newsService.newsListByPageNumber(pageNumber);
				request.setAttribute(JSPParameter.JSP_PAGE_NUMBER_PARAM, pageNumber);
			} else {
				newsList = newsService.latestList(Constant.MAX_NEWS_NUMBER_PER_PAGE);
			}

			request.setAttribute(Atribute.NEWS, newsList);
			request.setAttribute(Atribute.PRESENTATION, Atribute.NEWS_LIST);
			request.setAttribute(Atribute.PAGE, pageList);
			session.setAttribute(SessionAtribute.PAGE_URL, Util.pageURL(JSPPageName.NEWS_LIST,
						JSPParameter.JSP_PAGE_NUMBER_PARAM, String.valueOf(pageNumber)));

			request.getRequestDispatcher(JSPPageName.BASE_LAYOUT).forward(request, response);
		} catch (ServiceException | NewsValidationException e) {
			e.printStackTrace();
			response.sendRedirect(JSPPageName.GO_TO_ERROR_PAGE);
		}
	}

	private int takePageNumber(HttpServletRequest request) {
		String pageNumber = request.getParameter(JSPParameter.JSP_PAGE_NUMBER_PARAM);
		if (pageNumber != null) {
			return Integer.parseInt(pageNumber);
		}
		return Constant.ONE_PAGE;
	}

}
