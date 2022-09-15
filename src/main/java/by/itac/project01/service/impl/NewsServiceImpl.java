package by.itac.project01.service.impl;

import java.util.ArrayList;
import java.util.List;

import by.itac.project01.bean.News;
import by.itac.project01.dao.DAOProvider;
import by.itac.project01.dao.NewsDAO;
import by.itac.project01.dao.NewsDAOException;
import by.itac.project01.service.NewsService;
import by.itac.project01.service.ServiceException;
import by.itac.project01.service.validation.NewsValidationException;
import by.itac.project01.service.validation.NewsValidationService;
import by.itac.project01.service.validation.ValidationProvider;
import by.itac.project01.util.Constant;

public class NewsServiceImpl implements NewsService {
	private final NewsDAO newsDAO = DAOProvider.getInstance().getNewsDAO();
	private final NewsValidationService newsValidationService = ValidationProvider.getInstance()
			.getNewsValidationService();

	@Override
	public int save(News news, int reporterID) throws ServiceException, NewsValidationException {
		if (!newsValidationService.addNewsDataValidation(news)) {
			throw new NewsValidationException("Error news validation");
		}

		if (!newsValidationService.isNumberValidation(reporterID)) {
			throw new NewsValidationException("Error id reporter validation");
		}

		try {

			return newsDAO.addNews(news, reporterID);
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public News findById(int id) throws ServiceException, NewsValidationException {
		if (!newsValidationService.isNumberValidation(id)) {
			throw new NewsValidationException("Error id news validation");
		}

		try {
			return newsDAO.findById(id);
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<News> latestList(int count) throws ServiceException, NewsValidationException {
		if (!newsValidationService.isNumberValidation(count)) {
			throw new NewsValidationException("Error count news validation");
		}

		try {
			return newsDAO.latestsList(count);
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<News> newsListByPageNumber(int pageNumber) throws ServiceException, NewsValidationException {
		if (!newsValidationService.isNumberValidation(pageNumber)) {
			throw new NewsValidationException("Error page number validation");
		}

		try {
			int countOfAllNews;
			int maxNewsNumberPerPage;

			countOfAllNews = newsDAO.countOfNews();
			maxNewsNumberPerPage = Constant.MAX_NEWS_NUMBER_PER_PAGE;

			if (countOfAllNews < maxNewsNumberPerPage) {
				maxNewsNumberPerPage = countOfAllNews;
			}

			int skip = (pageNumber - 1) * maxNewsNumberPerPage;

			return newsDAO.newsListForOnePage(skip, maxNewsNumberPerPage);
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Integer> pageList() throws ServiceException {

		try {
			List<Integer> pageList = new ArrayList<Integer>();
			double newsNumber;
			double pageNumber;

			newsNumber = newsDAO.countOfNews();
			pageNumber = newsNumber / Constant.MAX_NEWS_NUMBER_PER_PAGE;

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

	@Override
	public void updateNews(News news, int reporterID) throws ServiceException, NewsValidationException {

		if (!newsValidationService.isNumberValidation(reporterID)) {
			throw new NewsValidationException("Error id reporter validation");
		}

		if (!newsValidationService.addNewsDataValidation(news)) {
			throw new NewsValidationException("Error in edit news process");
		}

		try {
			newsDAO.updateNews(news, reporterID);
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void deleteNews(String[] idNewsArrStr) throws ServiceException, NewsValidationException {

		if (!newsValidationService.newsIdValidation(idNewsArrStr)) {
			throw new NewsValidationException("Error id news validation");
		}

		int[] idNewsArrInt = new int[idNewsArrStr.length];

		int i = 0;
		for (String idNews : idNewsArrStr) {
			idNewsArrInt[i] = Integer.parseInt(idNews);
			i++;
		}

		try {
			newsDAO.deleteNews(idNewsArrInt);
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}
	}

}
