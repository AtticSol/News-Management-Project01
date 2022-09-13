package by.itac.project01.service.validation.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import by.itac.project01.bean.News;
import by.itac.project01.service.validation.NewsValidationException;
import by.itac.project01.service.validation.NewsValidationService;
import by.itac.project01.util.Constant;
import by.itac.project01.util.InputNewsDataError;

public class NewsValidationServiceImpl implements NewsValidationService {

	private static final InputNewsDataError noError = InputNewsDataError.NO_ERROR;

	@Override
	public boolean addNewsDataValidation(News news) throws NewsValidationException {

		List<InputNewsDataError> errorList = new ArrayList<InputNewsDataError>();

		error(errorList, lengthError(news.getTitle(), Constant.MAX_TITLE_CHARACTERS_NUMBER,
				Constant.MIN_TITLE_CHARACTERS_NUMBER, InputNewsDataError.TITLE_ERROR));
		error(errorList, dateError(news.getNewsDate()));
		error(errorList, lengthError(news.getBriefNews(), Constant.MAX_BRIEF_CHARACTERS_NUMBER,
				Constant.MIN_BRIEF_CHARACTERS_NUMBER, InputNewsDataError.BRIEF_ERROR));
		error(errorList, lengthError(news.getContent(), Constant.MAX_CONTENT_CHARACTERS_NUMBER,
				Constant.MIN_CONTENT_CHARACTERS_NUMBER, InputNewsDataError.CONTENT_ERROR));

		if (!errorList.isEmpty()) {
			throw new NewsValidationException(errorList, "News not added/updated");
		}
		return true;
	}

	private InputNewsDataError dateError(LocalDate date) {
		if (date == null) {
			return InputNewsDataError.EMPTY_DATE_ERROR;
		}
		return noError;
	}

	@Override
	public boolean isNumberValidation(int number) throws NewsValidationException {
		if (number == Constant.NO_NUMBER) {
			throw new NewsValidationException();
		}
		return true;
	}

	@Override
	public boolean newsIdValidation(String[] idNewsArrStr) throws NewsValidationException {
		List<InputNewsDataError> errorList = new ArrayList<InputNewsDataError>();

		if (idNewsArrStr == null) {
			error(errorList, InputNewsDataError.NO_NEWS_TO_DELETE_ERROR);
			throw new NewsValidationException(errorList, "No news to delete");
		}
		return true;
	}

	private InputNewsDataError lengthError(String validatedParam, int maxCharactersNumber, int minCharactersNumber,
			InputNewsDataError error) {
		if (validatedParam.length() >= maxCharactersNumber || validatedParam.length() < minCharactersNumber) {
			return error;
		}
		return noError;
	}

	private List<InputNewsDataError> error(List<InputNewsDataError> errorList, InputNewsDataError error) {
		if (!error.equals(noError)) {
			errorList.add(error);
		}
		return errorList;
	}
}
