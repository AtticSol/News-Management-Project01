package by.itac.project01.service;

import by.itac.project01.bean.NewUserInfo;
import by.itac.project01.service.validation.UserValidationException;

public interface UserService {
	
	String signIn(String login, String password) throws ServiceException, UserValidationException;
	boolean registration(NewUserInfo user) throws ServiceException, UserValidationException;
	
}
