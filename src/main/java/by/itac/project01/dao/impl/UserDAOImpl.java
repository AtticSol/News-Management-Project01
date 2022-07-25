package by.itac.project01.dao.impl;

import by.itac.project01.bean.NewUserInfo;
import by.itac.project01.dao.UserDAO;
import by.itac.project01.dao.exception.UserDAOException;

public class UserDAOImpl implements UserDAO{

	@Override
	public boolean logination(String login, String password) throws UserDAOException {
		/*
		 * Random rand = new Random(); int value = rand.nextInt(1000);
		 * 
		 * if(value % 3 == 0) { try { throw new SQLException("stub exception");
		 * }catch(SQLException e) { throw new DaoException(e); } }else if (value % 2 ==
		 * 0) { return true; }else { return false; }
		 */
		
		return true;
	}

	@Override
	public String getRole(String login, String password) throws UserDAOException {
		return "user";
	}

	@Override
	public boolean registration(NewUserInfo user) throws UserDAOException {
		
		return true;
	}

}
