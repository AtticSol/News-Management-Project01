package by.itac.project01.service.validation;

import by.itac.project01.service.validation.impl.UserValidationServiceImpl;

public class ValidationProvider {
	private static final ValidationProvider instance = new ValidationProvider();
	private final UserValidationService userValidationService = new UserValidationServiceImpl();
	
	private ValidationProvider() {}
	
	public static ValidationProvider getInstance() {
		return instance;
	}
	
	public UserValidationService getUserValidationService() {
		return userValidationService;
	}

}
