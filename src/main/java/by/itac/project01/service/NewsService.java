package by.itac.project01.service;

import java.util.List;

import by.itac.project01.bean.News;
import by.itac.project01.service.validation.NewsValidationException;

public interface NewsService {
	int save(News news, int reporterID) throws ServiceException, NewsValidationException;

	News findById(int idNews) throws ServiceException, NewsValidationException;

	List<News> latestList(int count) throws ServiceException, NewsValidationException;

	List<News> newsListByPageNumber(int pageNumber) throws ServiceException, NewsValidationException;

	List<Integer> pageList() throws ServiceException;

	void updateNews(News news, int reporterID) throws ServiceException, NewsValidationException;

	void deleteNews(String[] idNewsArr) throws ServiceException, NewsValidationException;

}
