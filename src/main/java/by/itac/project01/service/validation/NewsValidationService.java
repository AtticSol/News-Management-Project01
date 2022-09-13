package by.itac.project01.service.validation;

import by.itac.project01.bean.News;

public interface NewsValidationService {

	boolean addNewsDataValidation(News news) throws NewsValidationException;

	boolean newsIdValidation(String[] idNewsArrStr) throws NewsValidationException;

	boolean isNumberValidation(int id) throws NewsValidationException;

}
