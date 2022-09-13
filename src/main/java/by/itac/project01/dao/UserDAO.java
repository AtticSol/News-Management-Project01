package by.itac.project01.dao;

import by.itac.project01.bean.NewUserInfo;

public interface UserDAO {

	int userID(String login) throws UserDAOException;

	String role(int userID) throws UserDAOException;

	int registration(NewUserInfo user) throws UserDAOException;

	boolean isLogin(String login) throws UserDAOException;
	
	String takePassword(String login) throws UserDAOException;

	boolean isEmail(String email) throws UserDAOException;

}
