package by.itac.project01.service.validation;

import java.util.ArrayList;
import java.util.List;

import by.itac.project01.util.InputNewsDataError;

public class NewsValidationException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private List <InputNewsDataError> errorList = new ArrayList<InputNewsDataError>();
	
	public NewsValidationException() {
		super();
	}

	public NewsValidationException(String message) {
		super(message);
	}

	public NewsValidationException(Exception e) {
		super(e);
	}

	public NewsValidationException(String message, Exception ex) {
		super(message, ex);
	}
	
	public NewsValidationException(List<InputNewsDataError> errorList, String message) {
		super(message);
		this.errorList = errorList;
	}

	public List<InputNewsDataError> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<InputNewsDataError> errorList) {
		this.errorList = errorList;
	}
	
}
