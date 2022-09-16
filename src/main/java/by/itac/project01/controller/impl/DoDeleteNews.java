package by.itac.project01.controller.impl;

import java.io.IOException;
import java.util.List;

import by.itac.project01.controller.Atribute;
import by.itac.project01.controller.Command;
import by.itac.project01.controller.JSPPageName;
import by.itac.project01.controller.JSPParameter;
import by.itac.project01.controller.Role;
import by.itac.project01.service.NewsService;
import by.itac.project01.service.ServiceException;
import by.itac.project01.service.ServiceProvider;
import by.itac.project01.service.validation.NewsValidationException;
import by.itac.project01.util.InputNewsDataError;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DoDeleteNews implements Command {
	private final NewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] newsIDArr;
		HttpSession session;
		String role;

		session = request.getSession(false);
		
		role = (String) session.getAttribute(Role.ROLE.getTitle());
		newsIDArr = request.getParameterValues(JSPParameter.JSP_ID_NEWS_PARAM);

		try {
			newsService.deleteNews(newsIDArr);
			session.removeAttribute(Atribute.NEWS);
			response.sendRedirect(Util.pageURL(JSPPageName.NEWS_LIST,
					Atribute.PRESENTATION, Atribute.NEWS_LIST));
			}
		} catch (ServiceException e) {
			e.printStackTrace();
			response.sendRedirect(JSPPageName.GO_TO_ERROR_PAGE);
		} catch (NewsValidationException e) {
			e.printStackTrace();
			response.sendRedirect(Util.pageURL(JSPPageName.NEWS_LIST,
					Atribute.PRESENTATION, Atribute.NEWS_LIST) + inputErrorList(e));
		}

	}
	
	
	private String inputErrorList(NewsValidationException e) {
		String errors = "";
		List<InputNewsDataError> errorList;

		errorList = e.getErrorList();

		for (InputNewsDataError error : errorList) {
			errors = errors + Atribute.SEPARATOR + error.getTitle() + Atribute.EQUALS + error.getTitle();
		}
		return errors;
	}
}
