package by.itac.project01.dao;

import java.util.List;

import by.itac.project01.bean.News;

public interface NewsDAO {
	
	List<News> getList() throws NewsDAOException;
	List<News> getLatestsList(int count) throws NewsDAOException;
	News fetchById(int id) throws NewsDAOException;
	int addNews(News news) throws NewsDAOException;
	void updateNews(News news) throws NewsDAOException;
	void deleteNewses(String[] idNewses)throws NewsDAOException;
}
