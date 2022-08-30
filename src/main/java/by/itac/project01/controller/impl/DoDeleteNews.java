package by.itac.project01.controller.impl;

import java.io.IOException;

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

public class DoDeleteNews implements Command {
	private final NewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] idNewsArr = request.getParameterValues(JSPParameter.JSP_ID_NEWS_PARAM);

		try {
			newsService.deleteNews(idNewsArr);
			request.getSession(true).removeAttribute(Atribute.NEWS);
			response.sendRedirect(path());
		} catch (ServiceException e) {
			e.printStackTrace();
			response.sendRedirect(JSPPageName.ERROR_PAGE);
		}
	}

	private String path() {
		StringBuffer sb = new StringBuffer();
		sb.append(JSPPageName.NEWS_LIST);
		sb.append(Atribute.SEPARATOR);
		sb.append(Atribute.PRESENTATION);
		sb.append(Atribute.EQUALS);
		sb.append(Atribute.NEWS_LIST);
		
		return sb.toString();
	}
}
