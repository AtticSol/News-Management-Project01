package by.itac.project01.util;

public enum InputDataError {
	
	LOGIN_FOUND("Login was found"),
	LOGIN_MIN_LENGTH("Login must be at least 6 characters"),
	LOGIN_EXISTS("Login is already exists"),
	LOGIN_ERROR("loginError"),
	PASSWORD_ERROR("passwordError"),
	PASSWORD_CREATE_ERROR("Password must be at least 6 characters "
			+ "and contains one or more numbers, uppercase, lowercase and symbols"),
	CONFIRM_PASSWORD_ERROR("Passwords do not match"),
	EMAIL_FOUND(""),
	EMAIL_EXISTS("Email is already registered"),
	EMAIL_INCORRECT("Email is incorrect"),
	EMAIL_ERROR("emailError"),
	NO_ERROR("No error");
	
	private String title;
	
	InputDataError(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String errorName() {
		if (this.name().equals(LOGIN_EXISTS.name()) || 
				this.name().equals(LOGIN_MIN_LENGTH.name())) {
			return LOGIN_ERROR.title;
			
		} else if (this.name().equals(PASSWORD_CREATE_ERROR.name()) || 
				this.name().equals(CONFIRM_PASSWORD_ERROR.name())){
			return PASSWORD_ERROR.title;
			
		} else if (this.name().equals(EMAIL_EXISTS.name()) || 
				this.name().equals(EMAIL_INCORRECT.name())){
			return EMAIL_ERROR.title;
		} else {
			return this.name().toLowerCase();
		}
	}
	

}
