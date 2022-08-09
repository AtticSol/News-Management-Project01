package by.itac.project01.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import by.itac.project01.bean.NewUserInfo;
import by.itac.project01.connection.ConnectionPool;
import by.itac.project01.connection.exception.ConnectionPoolException;
import by.itac.project01.dao.UserDAO;
import by.itac.project01.dao.exception.UserDAOException;

public class UserDAOImpl implements UserDAO {

	@Override
	public boolean logination(String login, String password) throws UserDAOException {
		return true;
	}

	@Override
	public String getRole(String login, String password) throws UserDAOException {
		return "user";
	}

	public boolean registration(NewUserInfo user) throws UserDAOException {

		try (Connection con = ConnectionPool.getInstanceCP().takeConnection(); Statement st = con.createStatement()) {

			if (registrationDataTransaction(st, con, user)) {
				return true;
			}

		} catch (SQLException | ConnectionPoolException e) {
			throw new UserDAOException("Registration failed", e);
		}

		return false;

	}

	private boolean registrationDataTransaction(Statement st, Connection con, NewUserInfo user) throws SQLException {
		con.setAutoCommit(false);
		String addMainUserDataSQLRequest = "INSERT INTO users(login, password) VALUES(\"" + user.getLogin() + "\",\""
											+ user.getPassword() + "\")";
		String addAdditionalUserDataSQLRequest = "INSERT INTO user_details(users_id, name, email) VALUES(LAST_INSERT_ID(),\""
											+ user.getName() + "\",\"" + user.getEmail() + "\")";
		try {
			st.executeUpdate(addMainUserDataSQLRequest);
			st.executeUpdate(addAdditionalUserDataSQLRequest);
			con.commit();
			return true;
		} catch (SQLException e) {
			con.rollback();
		}
		return false;
	}

	@Override
	public boolean isLogin(String login) throws UserDAOException {

		String selectLoginSQLRequest = "SELECT login FROM users";
		try (Connection con = ConnectionPool.getInstanceCP().takeConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(selectLoginSQLRequest)) {
			while (rs.next()) {
				if ((rs.getString(1)).equals(login)) {
					return true;
				}
			}
		} catch (SQLException | ConnectionPoolException e) {
			throw new UserDAOException("Error login searching", e);
		}
		return false;
	}

	@Override
	public boolean isEmail(String email) throws UserDAOException {

		String sql = "SELECT * FROM user_details";
		try (Connection con = ConnectionPool.getInstanceCP().takeConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery(sql)) {

			while (rs.next()) {
				if ((rs.getString("email")).equals(email)) {
					return true;
				}
			}
		} catch (SQLException | ConnectionPoolException e) {
			throw new UserDAOException("Error email searching", e);
		}
		return false;
	}
}
