package by.itac.project01.service.validation;

import by.itac.project01.bean.NewUserInfo;
import by.itac.project01.service.exception.UserValidationException;

public interface UserValidationService {
	public boolean inputRegistrationData(NewUserInfo user) throws UserValidationException;
	public boolean inputAithorizationData(NewUserInfo user);	
}
