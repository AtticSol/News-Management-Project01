package by.itac.project01.service;

import java.util.List;

import by.itac.project01.bean.News;

public interface NewsService {
	int save(News news) throws ServiceException;

	News findById(int idNews) throws ServiceException;

	List<News> latestList(int count) throws ServiceException;

	List<News> newsListByPageNumber(int pageItem, int maxNewsNumberPerPage) throws ServiceException;

	List<Integer> pageList() throws ServiceException;

	void updateNews(News news) throws ServiceException;

	void deleteNews(String[] idNewsArr) throws ServiceException;

}
