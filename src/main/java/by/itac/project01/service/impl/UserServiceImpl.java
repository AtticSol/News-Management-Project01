package by.itac.project01.service.impl;

import by.itac.project01.bean.NewUserInfo;
import by.itac.project01.dao.DAOProvider;
import by.itac.project01.dao.UserDAO;
import by.itac.project01.dao.exception.UserDAOException;
import by.itac.project01.service.UserService;
import by.itac.project01.service.exception.ServiceException;
import by.itac.project01.service.exception.UserValidationException;
import by.itac.project01.service.validation.UserValidationService;
import by.itac.project01.service.validation.ValidationProvider;

public class UserServiceImpl implements UserService{
	
	private final UserDAO userDAO = DAOProvider.getInstance().getUserDAO();
	private final UserValidationService userValidationService = ValidationProvider.getInstance().getUserValidationService();

	@Override
	public String signIn(String login, String password) throws ServiceException {
		
		/*
		 * if(!userDataValidation.checkAUthData(login, password)) { throw new
		 * ServiceException("login ...... "); }
		 */
		
		try {
			if(userDAO.logination(login, password)) {
				return userDAO.getRole(login, password);
			} else {
				return "guest";
			}
		} catch (UserDAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean registration(NewUserInfo user) throws ServiceException, UserValidationException {
		
		if (!userValidationService.inputRegistrationData(user)) {
			throw new UserValidationException("Error validation");
		}
		
		try {
			if(userDAO.registration(user)) {
				return true;
			} else {
				return false;
			}
		} catch (UserDAOException e) {
			throw new ServiceException(e);
		}
	}

}
