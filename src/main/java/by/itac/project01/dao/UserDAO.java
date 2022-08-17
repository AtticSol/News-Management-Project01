package by.itac.project01.dao;

import by.itac.project01.bean.NewUserInfo;

public interface UserDAO {
	
	boolean logination(String login, String password) throws UserDAOException;
	boolean registration(NewUserInfo user) throws UserDAOException;
	String getRole(String login, String password) throws UserDAOException;
	boolean isLogin(String login) throws UserDAOException;
	boolean isCorrectPassword(String login, String password) throws UserDAOException;
	boolean isEmail(String email) throws UserDAOException;
		
}
