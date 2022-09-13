package by.itac.project01.service;

import by.itac.project01.bean.NewUserInfo;
import by.itac.project01.service.validation.UserValidationException;

public interface UserService {

	int userID(String login, String password) throws ServiceException, UserValidationException;

	String role(int userID) throws ServiceException, UserValidationException;

	int registration(NewUserInfo user) throws ServiceException, UserValidationException;

}
