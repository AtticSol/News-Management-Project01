package by.itac.project01.service.validation.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	public boolean inputAithorizationData(NewUserInfo user) {
		// TODO Auto-generated method stub
		return false;
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
			if (email.equals("")) {
				return noError;
			} else if (matcher.matches()) {
				if (isEmail(email)) {
					return InputDataError.EMAIL_EXISTS;
				} else {
					return noError;
				}
			} else {
				return InputDataError.EMAIL_INCORRECT;
			}
		} catch (UserDAOException e) {
			throw new UserValidationException(e);
		}
	}

	private InputDataError checkPassword(String password, String confirmPassword) {
		if (!isValidPassword(password)) {
			return InputDataError.PASSWORD_CREATE_ERROR;
		} else if (!password.equals(confirmPassword)) {
			return InputDataError.CONFIRM_PASSWORD_ERROR;
		} else {
			return noError;
		}
	}

	private boolean isValidPassword(String password) {
		return password.matches(PASSWORD_REGEX);
	}

	private InputDataError checkLogin(String login) throws UserValidationException {
		try {
			if (login.length() < 6) {
				return InputDataError.LOGIN_MIN_LENGTH;
			} else if (userDAO.isLogin(login)) {
				return InputDataError.LOGIN_EXISTS;
			} else {
				return noError;
			}
		} catch (UserDAOException e) {
			throw new UserValidationException(e);
		}
	}

}
