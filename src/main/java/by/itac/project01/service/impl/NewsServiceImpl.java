package by.itac.project01.service.impl;

import java.util.List;

import by.itac.project01.bean.News;
import by.itac.project01.dao.DAOProvider;
import by.itac.project01.dao.NewsDAO;
import by.itac.project01.dao.NewsDAOException;
import by.itac.project01.service.NewsService;
import by.itac.project01.service.ServiceException;

public class NewsServiceImpl implements NewsService{
	private final NewsDAO newsDAO = DAOProvider.getInstance().getNewsDAO();

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void find() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<News> latestList(int count) throws ServiceException{
		try {
			return newsDAO.getLatestsList(5);
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<News> list() throws ServiceException{
		try {
			return newsDAO.getList();
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}
	}
	
	

	@Override
	public News findById(int id) throws ServiceException{
		try {
			return newsDAO.fetchById(id);
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}
	}

}
