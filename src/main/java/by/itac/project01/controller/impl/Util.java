package by.itac.project01.controller.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import by.itac.project01.controller.Atribute;
import by.itac.project01.service.validation.NewsValidationException;
import by.itac.project01.util.Constant;
import by.itac.project01.util.InputNewsDataError;

public class Util {

	
	// first argument - command, others - attributes with values
	static String pageURL(String command, String... param) {
		StringBuffer sb = new StringBuffer();
		sb.append(command);
		for (int i = 0; i < param.length; i = i + 2) {
			sb.append(Atribute.SEPARATOR);
			sb.append(param[i]);
			sb.append(Atribute.EQUALS);
			sb.append(param[i + 1]);
		}
		return sb.toString();
	}

	static int takeNumber(String inputNumber) {
		if (!StringUtils.isNumeric(inputNumber)) {
			return Constant.NO_NUMBER;
		} else if (inputNumber.isEmpty()) {
			return Constant.NO_NUMBER;
		} else {
			return Integer.parseInt(inputNumber);
		}
	}
	
	static String inputErrorList(NewsValidationException e) {
		String errors = "";
		List<InputNewsDataError> errorList;

		errorList = e.getErrorList();

		for (InputNewsDataError error : errorList) {
			errors = errors + Atribute.SEPARATOR + error.getTitle() + Atribute.EQUALS + error.getTitle();
		}
		return errors;
	}
}
