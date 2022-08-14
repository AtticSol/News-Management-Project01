package by.itac.project01.service.validation;

import by.itac.project01.bean.NewUserInfo;
import by.itac.project01.service.exception.UserValidationException;

public interface UserValidationService {
	boolean inputRegistrationData(NewUserInfo user) throws UserValidationException;
	boolean inputAithorizationData(String login, String password) throws UserValidationException;	
}
