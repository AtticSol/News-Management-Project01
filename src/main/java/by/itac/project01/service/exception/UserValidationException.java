package by.itac.project01.service.exception;

import java.util.ArrayList;
import java.util.List;

import by.itac.project01.util.InputDataError;

public class UserValidationException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private List <InputDataError> errorList = new ArrayList<InputDataError>();
	
	public UserValidationException() {
		super();
	}

	public UserValidationException(String e) {
		super(e);
	}

	public UserValidationException(Exception e) {
		super(e);
	}

	public UserValidationException(String e, Exception ex) {
		super(e, ex);
	}
	
	public UserValidationException(List<InputDataError> errorList, String message) {
		super(message);
		this.errorList = errorList;
	}

	public List<InputDataError> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<InputDataError> errorList) {
		this.errorList = errorList;
	}

}
