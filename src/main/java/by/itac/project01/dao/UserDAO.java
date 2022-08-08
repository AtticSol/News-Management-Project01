package by.itac.project01.dao;

import by.itac.project01.bean.NewUserInfo;
import by.itac.project01.dao.exception.UserDAOException;

public interface UserDAO {
	
	public boolean logination(String login, String password) throws UserDAOException;
	public boolean registration(NewUserInfo user) throws UserDAOException;
	public String getRole(String login, String password) throws UserDAOException;
	public boolean isLogin(String login) throws UserDAOException;
	public boolean isEmail(String email) throws UserDAOException;
	

	
}
