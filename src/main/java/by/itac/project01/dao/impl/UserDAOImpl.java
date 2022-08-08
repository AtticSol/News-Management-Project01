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

	@Override
	public boolean registration(NewUserInfo user) throws UserDAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = ConnectionPool.getInstanceCP().takeConnection();

			String sql = "INSERT INTO users(login, password) VALUES(?,?)";
			ps = con.prepareStatement(sql);

			ps.setString(1, user.getLogin());
			ps.setString(2, user.getPassword());

			ps.executeUpdate();

			boolean addDetails = addUserDetails(user, con, ps, rs);
			if (addDetails) {
				return true;
			}

		} catch (SQLException | ConnectionPoolException e) {
			throw new UserDAOException("Registration failed", e);

		} finally {
			try {
				ConnectionPool.getInstanceCP().closeConnection(con, ps);
			} catch (ConnectionPoolException e) {
				throw new UserDAOException("Error closing the connection", e);
			}
		}

		return false;

	}

	private boolean addUserDetails(NewUserInfo user, Connection con, PreparedStatement ps, ResultSet rs)
			throws UserDAOException {
		try {

			rs = ps.executeQuery("SELECT * FROM users");
			int userId = 0;
			while (rs.next()) {
				userId = rs.getInt("id");
			}

			String sql = "INSERT INTO user_details(users_id, name, email) VALUES(?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setString(2, user.getName());
			ps.setString(3, user.getEmail());

			int n = 0;
			n = ps.executeUpdate();

			if (n != 0) {
				return true;
			}
		} catch (SQLException e) {
			throw new UserDAOException("Add user_details failed", e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				throw new UserDAOException("Error closing ResultSet connection", e);
			}
		}
		return false;
	}

	@Override
	public boolean isLogin(String login) throws UserDAOException {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			con = ConnectionPool.getInstanceCP().takeConnection();
			st = con.createStatement();
			String sql = "SELECT login FROM users";
			rs = st.executeQuery(sql);
			while (rs.next()) {
				if ((rs.getString(1)).equals(login)) {
					return true;
				}
			}
		} catch (SQLException | ConnectionPoolException e) {
			throw new UserDAOException("Error login searching", e);
		} finally {
			try {
				if (rs != null) {
					ConnectionPool.getInstanceCP().closeConnection(con, st, rs);
				} else {
					ConnectionPool.getInstanceCP().closeConnection(con, st);
				}
			} catch (ConnectionPoolException e) {
				throw new UserDAOException("Error closing the connection", e);
			}
		}
		return false;
	}

	@Override
	public boolean isEmail(String email) throws UserDAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String emailBD;
		
		try {
			con = ConnectionPool.getInstanceCP().takeConnection();

			String sql = "SELECT email FROM user_details";
			ps = con.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();
			if (rs.next()) {
				emailBD = rs.getString(1);
				if (emailBD.equals(email)) {
					return true;
				}
				//noEmail
			}
		} catch (SQLException | ConnectionPoolException e) {
			throw new UserDAOException("Error email searching", e);
		} finally {
			try {
				if (rs != null ) {
					ConnectionPool.getInstanceCP().closeConnection(con, ps, rs);
				} else {
					ConnectionPool.getInstanceCP().closeConnection(con, ps);
				}
			} catch (ConnectionPoolException e) {
				throw new UserDAOException("Error closing the connection", e);
			}
		}		
		return false;
	}
}
