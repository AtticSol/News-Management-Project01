package by.itac.project01.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.itac.project01.bean.NewUserInfo;
import by.itac.project01.dao.UserDAO;
import by.itac.project01.dao.UserDAOException;
import by.itac.project01.dao.connection.ConnectionPool;
import by.itac.project01.dao.connection.ConnectionPoolException;

public class UserDAOImpl implements UserDAO {
	private static final Logger log = LogManager.getRootLogger();

	private static final String ID_COLUMN = "id";
	private static final String GET_ID_BY_LOGIN_SQL_REQUEST = "SELECT id FROM users WHERE login=?";

	@Override
	public int getID(String login) throws UserDAOException { 
		try (Connection con = ConnectionPool.getInstanceCP().takeConnection();
				PreparedStatement ps = con.prepareStatement(GET_ID_BY_LOGIN_SQL_REQUEST)) {

			ps.setString(1, login);
			ResultSet rs = ps.executeQuery();
			rs.next();

			return (rs.getInt(ID_COLUMN));

		} catch (SQLException | ConnectionPoolException e) {
			throw new UserDAOException("Error id searching", e);
		}
	}

	
	private static final String ROLE_COLUMN = "role";
	private static final String GET_ROLE_BY_ID_SQL_REQUEST = "SELECT role FROM users WHERE id=?";
	
	@Override
	public String getRole(int userID) throws UserDAOException {
		try (Connection con = ConnectionPool.getInstanceCP().takeConnection();
				PreparedStatement ps = con.prepareStatement(GET_ROLE_BY_ID_SQL_REQUEST)) {

			ps.setInt(1, userID);
			ResultSet rs = ps.executeQuery();
			rs.next();

			return (rs.getString(ROLE_COLUMN));

		} catch (SQLException | ConnectionPoolException e) {
			throw new UserDAOException("Error role searching", e);
		}

	}

	private static final String ADD_USER_SQL_REQUEST = "INSERT INTO users(login, password, role) VALUES(?,?,?)";
	private static final String ADD_USER_DETAILS_SQL_REQUEST = "INSERT INTO user_details(users_id, name, email) VALUES(LAST_INSERT_ID(),?,?)";
	
	@Override
	public boolean registration(NewUserInfo user) throws UserDAOException {
		try (Connection con = ConnectionPool.getInstanceCP().takeConnection();
				PreparedStatement ps1 = con.prepareStatement(ADD_USER_SQL_REQUEST);
				PreparedStatement ps2 = con.prepareStatement(ADD_USER_DETAILS_SQL_REQUEST)) {
			
			return registrationDataTransaction(ps1, ps2, con, user);

		} catch (SQLException | ConnectionPoolException e) {
			log.log(Level.ERROR, "Registration failed", e);
			throw new UserDAOException("Registration failed", e);
		}

	}

	private boolean registrationDataTransaction(PreparedStatement ps1, PreparedStatement ps2, 
			Connection con, NewUserInfo user) throws SQLException {		
		
		con.setAutoCommit(false);

		ps1.setString(1, user.getLogin());
		ps1.setString(2, user.getPassword());
		ps1.setString(3, user.getRole());
		
		ps2.setString(1, user.getName());
		ps2.setString(2, user.getEmail());

		try {
			ps1.executeUpdate();
			ps2.executeUpdate();
			con.commit();
			return true;

		} catch (SQLException e) {
			con.rollback();
		}
		return false;
	}

	
	private static final String LOGIN_COLUMN = "login";
	private static final String GET_LOGIN_SQL_REQUEST = "SELECT login FROM users";

	@Override
	public boolean isLogin(String login) throws UserDAOException {

		try (Connection con = ConnectionPool.getInstanceCP().takeConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(GET_LOGIN_SQL_REQUEST)) {

			while (rs.next()) {
				if ((rs.getString(LOGIN_COLUMN)).equals(login)) {
					return true;
				}
			}

		} catch (SQLException | ConnectionPoolException e) {
			throw new UserDAOException("Error login searching", e);
		}
		return false;
	}

	
	private static final String PASSWORD_COLUMN = "password";
	private static final String GET_PASSWORD_SQL_REQUEST = "SELECT password FROM users WHERE login=?";

	@Override
	public boolean isCorrectPassword(String login, String password) throws UserDAOException {
		try (Connection con = ConnectionPool.getInstanceCP().takeConnection();
				PreparedStatement ps = con.prepareStatement(GET_PASSWORD_SQL_REQUEST)) {

			ps.setString(1, login);
			ResultSet rs = ps.executeQuery();
			rs.next();

			return rs.getString(PASSWORD_COLUMN).equals(password);

		} catch (SQLException | ConnectionPoolException e) {
			throw new UserDAOException("Error validation password", e);
		}
	}

	private static final String EMAIL_COLUMN = "email";
	private static final String GET_EMAIL_SQL_REQUEST = "SELECT * FROM user_details";

	@Override
	public boolean isEmail(String email) throws UserDAOException {
		try (Connection con = ConnectionPool.getInstanceCP().takeConnection();
				PreparedStatement ps = con.prepareStatement(GET_EMAIL_SQL_REQUEST);
				ResultSet rs = ps.executeQuery(GET_EMAIL_SQL_REQUEST)) {

			while (rs.next()) {
				if ((rs.getString(EMAIL_COLUMN)).equals(email)) {
					return true;
				}
			}
		} catch (SQLException | ConnectionPoolException e) {
			throw new UserDAOException("Error email searching", e);
		}
		return false;
	}

}
