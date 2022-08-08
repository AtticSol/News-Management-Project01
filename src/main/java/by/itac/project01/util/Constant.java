package by.itac.project01.util;

public class Constant {
	public static final String EQUALS = "=";
	public static final String SEPARATOR = "&";
	
	public static final String AUTHENTICATION_ERROR = "AuthenticationError";
	public static final String AUTHENTICATION_ERROR_VALUE = "Wrong login or password";
	public static final String REGISTRATION_ERROR = "RegistrationError";
	public static final String REGISTRATION_ERROR_VALUE = "Registration Error";
	
	public static final String USER = "user";
	public static final String USER_NOT_REGISTERED = "not_registered";
	public static final String USER_ACTIVE = "active";
	public static final String USER_NOT_ACTIVE = "not active";

	
	public static final String ROLE = "role";
	public static final String ROLE_USER = "user";
	public static final String ROLE_GUEST = "guest";
	public static final String ROLE_ADMIN = "admin";
	
	
	public static final String REGISTRATION_ERROR_PAGE = "controller?command=go_to_base_page";
	public static final String AUTHENTICATION_ERROR_PAGE = "controller?command=go_to_base_page";
	public static final String NEWS_LIST = "controller?command=go_to_news_list";
	public static final String INDEX_JSP = "index.jsp";
	public static final String BASE_LAYOUT = "WEB-INF/pages/layouts/baseLayout.jsp";
	
	public static final String JSP_NAME_PARAM = "name";
	public static final String JSP_LOGIN_PARAM = "login";
	public static final String JSP_PASSWORD_PARAM = "password";
	public static final String JSP_CONFIRM_PASSWORD_PARAM = "confirm_password";
	public static final String JSP_EMAIL_PARAM = "email";
	


}
