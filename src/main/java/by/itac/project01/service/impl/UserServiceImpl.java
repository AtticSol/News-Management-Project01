package by.itac.project01.service.impl;

import by.itac.project01.bean.NewUserInfo;
import by.itac.project01.dao.DAOProvider;
import by.itac.project01.dao.UserDAO;
import by.itac.project01.dao.UserDAOException;
import by.itac.project01.service.ServiceException;
import by.itac.project01.service.UserService;
import by.itac.project01.service.validation.UserValidationException;
import by.itac.project01.service.validation.UserValidationService;
import by.itac.project01.service.validation.ValidationProvider;

public class UserServiceImpl implements UserService {

	private final UserDAO userDAO = DAOProvider.getInstance().getUserDAO();
	private final UserValidationService userValidationService = ValidationProvider.getInstance().getUserValidationService();

	@Override
	public int userID(String login, String password) throws ServiceException, UserValidationException {

		if (!userValidationService.inputAithorizationData(login, password)) {
			throw new UserValidationException("Error validation");
		}

		try {
			return userDAO.getID(login);
		} catch (UserDAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public String getRole(int userID) throws ServiceException {

		try {
			return userDAO.getRole(userID);
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
			if (!userDAO.registration(user)) {
				return false;
			}

		} catch (UserDAOException e) {
			throw new ServiceException(e);
		}
		return true;
	}

}
