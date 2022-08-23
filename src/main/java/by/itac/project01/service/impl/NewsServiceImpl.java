package by.itac.project01.service.impl;

import java.util.ArrayList;
import java.util.List;

import by.itac.project01.bean.News;
import by.itac.project01.dao.DAOProvider;
import by.itac.project01.dao.NewsDAO;
import by.itac.project01.dao.NewsDAOException;
import by.itac.project01.service.NewsService;
import by.itac.project01.service.ServiceException;
import by.itac.project01.util.NewsParameter;

public class NewsServiceImpl implements NewsService {
	private final NewsDAO newsDAO = DAOProvider.getInstance().getNewsDAO();

	@Override
	public void save(News news) throws ServiceException {
		// validation

		try {
			newsDAO.addNews(news);
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public News findById(int id) throws ServiceException {
		// validation
		try {
			return newsDAO.fetchById(id);
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<News> latestList(int count) throws ServiceException {
		// validation
		try {
			return newsDAO.latestsList(count);
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<News> newsListByPageNumber(int pageItem, int maxNewsNumberPerPage) throws ServiceException {
		// validation
		try {
			int countOfAllNews;

			countOfAllNews = newsDAO.countOfNews();

			if (countOfAllNews < maxNewsNumberPerPage) {
				maxNewsNumberPerPage = countOfAllNews;
			}

			int skip = (pageItem - 1) * maxNewsNumberPerPage;

			return newsDAO.newsListForOnePage(skip, maxNewsNumberPerPage);
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Integer> pageList() throws ServiceException {
		// validation
		try {
			double newsNumber;
			double pageNumber;
			List<Integer> pageList = new ArrayList<Integer>();

			newsNumber = newsDAO.countOfNews();
			pageNumber = newsNumber / NewsParameter.MAX_NEWS_NUMBER_PER_PAGE;

			int i = 0;
			while (i < pageNumber) {
				pageList.add(i + 1);
				i++;
			}
			return pageList;

		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}

	}

//	@Override
//	public void find() {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void update() {
//		// TODO Auto-generated method stub
//
//	}

//	@Override
//	public List<News> list() throws ServiceException {
//		// validation
//		try {
//			return newsDAO.allNewsList();
//		} catch (NewsDAOException e) {
//			throw new ServiceException(e);
//		}
//	}

}
