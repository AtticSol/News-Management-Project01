package by.itac.project01.dao;

import java.util.List;

import by.itac.project01.bean.News;

public interface NewsDAO {
	
	List<News> latestsList(int count) throws NewsDAOException;
	List<News> newsListForOnePage (int skip, int count) throws NewsDAOException;
	int countOfNews() throws NewsDAOException;
	
	void addNews(News news) throws NewsDAOException;
	News fetchById(int id) throws NewsDAOException;
	
//	void updateNews(News news) throws NewsDAOException;
//	void deleteNewses(String[] idNewses)throws NewsDAOException;
	

//	List<News> allNewsList() throws NewsDAOException;

}
