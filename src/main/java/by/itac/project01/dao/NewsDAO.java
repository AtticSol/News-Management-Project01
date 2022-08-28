package by.itac.project01.dao;

import java.util.List;

import by.itac.project01.bean.News;

public interface NewsDAO {

	List<News> latestsList(int count) throws NewsDAOException;

	List<News> newsListForOnePage(int skip, int count) throws NewsDAOException;

	int countOfNews() throws NewsDAOException;

	int addNews(News news) throws NewsDAOException;

	News findById(int idNews) throws NewsDAOException;

	void updateNews(News news) throws NewsDAOException;

	void deleteNews(int[] idNews) throws NewsDAOException;

}
