package by.itac.project01.controller.filter;

import java.io.IOException;

import by.itac.project01.controller.Atribute;
import by.itac.project01.controller.CommandName;
import by.itac.project01.controller.JSPPageName;
import by.itac.project01.controller.Role;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SecurityFilter implements Filter {

	private ServletContext context;

	public void init(FilterConfig fConfig) throws ServletException {
		context = fConfig.getServletContext();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req;
		HttpSession session;
		Role role;
		CommandName command;

		req = (HttpServletRequest) request;
		session = req.getSession(false);

		if (session == null) {

			context.log("SessionSecurity was failed");
			req.setAttribute(Atribute.SESSION_ERROR, Atribute.SESSION_ERROR);
			req.getRequestDispatcher(JSPPageName.GO_TO_BASE_PAGE).forward(request, response);

		} else {

			role = Role.valueOf(takeRole(session).toUpperCase());
			command = CommandName.valueOf((req.getParameter(Atribute.COMMAND)).toUpperCase());

			switch (role) {
			
			case GUEST:
				
				switch (command) {
				case DO_CHANGE_LOCAL,
					DO_REGISTRATION,
					DO_SIGN_IN,
					GO_TO_BASE_PAGE,
					GO_TO_REGISTRATION_PAGE:

					context.log("SessionSecurity was done");
					chain.doFilter(request, response);
					break;

				default:
					req.setAttribute(Atribute.ACCESS_ERROR, Atribute.ACCESS_ERROR);
					req.getRequestDispatcher(JSPPageName.GO_TO_BASE_PAGE).forward(request, response);
					break;
				}
				break;
				
			case USER:

				switch (command) {
				case DO_CHANGE_LOCAL,
					DO_SIGN_OUT,
					GO_TO_BASE_PAGE,
					GO_TO_NEWS_LIST,
					GO_TO_VIEW_NEWS:

					context.log("SessionSecurity was done");
					chain.doFilter(request, response);
					break;

				default:
					req.setAttribute(Atribute.ACCESS_ERROR, Atribute.ACCESS_ERROR);
					req.getRequestDispatcher(JSPPageName.GO_TO_BASE_PAGE).forward(request, response);
					break;
				}
				break;
				
			case REPORTER:

				switch (command) {
				case DO_ADD_NEWS,
					DO_CANCEL,
					DO_CHANGE_LOCAL,
					DO_DELETE_NEWS,
					DO_EDIT_NEWS,
					DO_SIGN_OUT,
					GO_TO_ADD_NEWS,
					GO_TO_BASE_PAGE,
					GO_TO_EDIT_NEWS,
					GO_TO_NEWS_LIST,
					GO_TO_VIEW_NEWS:

					context.log("SessionSecurity was done.");
					chain.doFilter(request, response);
					break;

				default:
					req.setAttribute(Atribute.ACCESS_ERROR, Atribute.ACCESS_ERROR);
					req.getRequestDispatcher(JSPPageName.GO_TO_BASE_PAGE).forward(request, response);
					break;
				}
				break;
				
			case ADMIN:

				context.log("SessionSecurity was done");
				chain.doFilter(request, response);
				break;
				
			default:
				context.log("SessionSecurity was failed");
				req.setAttribute(Atribute.SESSION_ERROR, Atribute.SESSION_ERROR);
				req.getRequestDispatcher(JSPPageName.GO_TO_BASE_PAGE).forward(request, response);
				break;
			}
		}
	}

	public void destroy() {
	}

	private String takeRole(HttpSession session) {

		return (session.getAttribute(Role.ROLE.getTitle()) != null)
				? (String) session.getAttribute(Role.ROLE.getTitle())
				: Role.GUEST.getTitle();
	}
}
