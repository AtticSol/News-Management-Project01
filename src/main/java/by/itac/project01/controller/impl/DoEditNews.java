package by.itac.project01.controller.impl;

import java.io.IOException;
import java.time.LocalDate;

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

public class DoEditNews implements Command {
	private final NewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session;
		News news;
		int reporterID;
		int newsID;

		session = request.getSession(false);

		reporterID = Util.takeNumber(session.getAttribute(SessionAtribute.USER_ID).toString());
		newsID = Util.takeNumber(request.getParameter(JSPParameter.JSP_ID_NEWS_PARAM));

		news = newsInfoFromRequest(request);
		news.setNewsID(newsID);

		try {
			newsService.updateNews(news, reporterID);
			session.setAttribute(Atribute.NEWS, news);
			response.sendRedirect(Util.pageURL(JSPPageName.VIEW_NEWS, 
					Atribute.PRESENTATION, Atribute.VIEW_NEWS,
					Atribute.NEWS_ID, String.valueOf(newsID)));

		} catch (ServiceException e) {
			e.printStackTrace();
			response.sendRedirect(JSPPageName.GO_TO_ERROR_PAGE);
		} catch (NewsValidationException e) {
			e.printStackTrace();
			
			response.sendRedirect(Util.pageURL(JSPPageName.GO_TO_EDIT_NEWS,
					Atribute.NEWS_ID, String.valueOf(newsID),
					JSPParameter.JSP_PREVIOUS_PRESENTAION, Atribute.VIEW_NEWS)
					+ Util.inputErrorList(e));
		}
	}

	private News newsInfoFromRequest(HttpServletRequest request) {
		String title;
		String briefNews;
		String content;
		LocalDate newsDate;

		title = request.getParameter(JSPParameter.JSP_TITLE_PARAM);
		briefNews = request.getParameter(JSPParameter.JSP_BRIEF_PARAM);
		content = request.getParameter(JSPParameter.JSP_CONTENT_PARAM);

		if (!(request.getParameter(JSPParameter.JSP_DATE_NEWS_PARAM).isEmpty())) {
			newsDate = LocalDate.parse(request.getParameter(JSPParameter.JSP_DATE_NEWS_PARAM));
		} else {
			newsDate = null;
		}

		return new News(title, briefNews, content, newsDate);
	}

}
