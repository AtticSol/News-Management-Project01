package by.itac.project01.service.validation;

import by.itac.project01.bean.NewUserInfo;

public interface UserValidationService {
	boolean inputRegistrationDataValidation(NewUserInfo user) throws UserValidationException;

	boolean inputAithorizationDataValidation(String login, String password) throws UserValidationException;

	boolean userIDValidation(int userID) throws UserValidationException;
}
