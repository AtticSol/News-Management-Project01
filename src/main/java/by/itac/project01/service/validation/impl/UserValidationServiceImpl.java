package by.itac.project01.service.validation.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.itac.project01.bean.NewUserInfo;
import by.itac.project01.dao.DAOProvider;
import by.itac.project01.dao.UserDAO;
import by.itac.project01.dao.exception.UserDAOException;
import by.itac.project01.service.exception.UserValidationException;
import by.itac.project01.service.validation.UserValidationService;
import by.itac.project01.util.InputDataError;

public class UserValidationServiceImpl implements UserValidationService {

	private static final InputDataError noError = InputDataError.NO_ERROR;
	private final UserDAO userDAO = DAOProvider.getInstance().getUserDAO();

	private static final String PASSWORD_REGEX = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}";
	private static final String EMAIL_REGEX = "\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}";
	private static final Logger log = LogManager.getRootLogger();

	@Override
	public boolean inputRegistrationData(NewUserInfo user) throws UserValidationException {
		List<InputDataError> errorList = new ArrayList<InputDataError>();

		InputDataError loginError = checkLogin(user.getLogin());
		if (!loginError.equals(noError)) {
			errorList.add(loginError);
		}

		InputDataError passwordError = checkPassword(user.getPassword(), user.getConfirmPassword());
		if (!passwordError.equals(noError)) {
			errorList.add(passwordError);
		}

		InputDataError emailError = checkEmail(user.getEmail());
		if (!emailError.equals(noError)) {
			errorList.add(emailError);
		}

		if (!errorList.isEmpty()) {
			throw new UserValidationException(errorList, "User not added");
		}
		return true;
	}

	@Override
	public boolean inputAithorizationData(String login, String password) throws UserValidationException {
		if (!isLogin(login)) {
			return false;
		} else {
			if (!isCorrectPassword(login, password)) {
				return false;
			}
		}
		return true;
	}

	private boolean isCorrectPassword(String login, String password) throws UserValidationException {
		try {
			return userDAO.isCorrectPassword(login, password);
		} catch (UserDAOException e) {
			log.log(Level.ERROR, "Password validation failed", e);
			throw new UserValidationException(e);
		}
	}

	private boolean isLogin(String login) throws UserValidationException {
		try {
			return userDAO.isLogin(login);
		} catch (UserDAOException e) {
			log.log(Level.ERROR, "Login validation failed", e);
			throw new UserValidationException(e);
		}
	}

	private InputDataError checkPassword(String password, String confirmPassword) {
		if (!isValidPassword(password)) {
			return InputDataError.PASSWORD_CREATE_ERROR;
		} else if (!password.equals(confirmPassword)) {
			return InputDataError.CONFIRM_PASSWORD_ERROR;
		}
		return noError;
	}

	private boolean isValidPassword(String password) {
		return password.matches(PASSWORD_REGEX);
	}

	private InputDataError checkLogin(String login) throws UserValidationException {
		if (login.length() < 6) {
			return InputDataError.LOGIN_MIN_LENGTH;
		} else if (isLogin(login)) {
			return InputDataError.LOGIN_EXISTS;
		}
		return noError;
	}

	private boolean isEmail(String email) throws UserDAOException {
		return userDAO.isEmail(email);
	}

	private InputDataError checkEmail(String email) throws UserValidationException {
		Pattern pattern;
		Matcher matcher;
		pattern = Pattern.compile(EMAIL_REGEX);
		matcher = pattern.matcher(email);

		try {
			if (!email.equals("")) {
				if (!matcher.matches()) {
					return InputDataError.EMAIL_INCORRECT;
				}

				if (isEmail(email)) {
					return InputDataError.EMAIL_EXISTS;
				}

				return noError;
			}

		} catch (UserDAOException e) {
			throw new UserValidationException(e);
		}
		return noError;
	}

}
