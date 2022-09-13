package by.itac.project01.service.validation;

import java.util.ArrayList;
import java.util.List;

import by.itac.project01.util.InputUserDataError;

public class UserValidationException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private List <InputUserDataError> errorList = new ArrayList<InputUserDataError>();
	
	public UserValidationException() {
		super();
	}

	public UserValidationException(String message) {
		super(message);
	}

	public UserValidationException(Exception e) {
		super(e);
	}

	public UserValidationException(String message, Exception ex) {
		super(message, ex);
	}
	
	public UserValidationException(List<InputUserDataError> errorList, String message) {
		super(message);
		this.errorList = errorList;
	}

	public List<InputUserDataError> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<InputUserDataError> errorList) {
		this.errorList = errorList;
	}

}
