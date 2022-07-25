package by.itac.project01.service.impl;

import by.itac.project01.bean.NewUserInfo;
import by.itac.project01.dao.DAOProvider;
import by.itac.project01.dao.UserDAO;
import by.itac.project01.dao.exception.UserDAOException;
import by.itac.project01.service.UserService;
import by.itac.project01.service.exception.ServiceException;

public class UserServiceImpl implements UserService{
	
	private final UserDAO userDAO = DAOProvider.getInstance().getUserDAO();
//	private final UserDataValidation userDataValidation = ValidationProvider.getIntsance().getUserDataVelidation();

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
	public boolean registration(NewUserInfo user) throws ServiceException {
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
