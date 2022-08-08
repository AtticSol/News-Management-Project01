package by.itac.project01.service;

import by.itac.project01.bean.NewUserInfo;
import by.itac.project01.service.exception.ServiceException;
import by.itac.project01.service.exception.UserValidationException;

public interface UserService {
	
	String signIn(String login, String password) throws ServiceException;
	boolean registration(NewUserInfo user) throws ServiceException, UserValidationException;
	
}
