package by.itac.project01.service;

import by.itac.project01.bean.NewUserInfo;
import by.itac.project01.service.validation.UserValidationException;

public interface UserService {

	int userID(String login, String password) throws ServiceException, UserValidationException;

	String getRole(int userID) throws ServiceException, UserValidationException;

	boolean registration(NewUserInfo user) throws ServiceException, UserValidationException;

}
