package by.itac.project01.service;

import java.util.List;

import by.itac.project01.bean.News;

public interface NewsService {
	void save();
	void find();
	void update();
	
	List<News> latestList(int count) throws ServiceException;
	List<News> list() throws ServiceException;
	News findById(int id) throws ServiceException;
}
