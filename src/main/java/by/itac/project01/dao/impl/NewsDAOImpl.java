package by.itac.project01.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.itac.project01.bean.News;
import by.itac.project01.dao.NewsDAO;
import by.itac.project01.dao.NewsDAOException;
import by.itac.project01.dao.connection.ConnectionPool;
import by.itac.project01.dao.connection.ConnectionPoolException;
import by.itac.project01.util.NewsParameter;

public class NewsDAOImpl implements NewsDAO {

	@Override
	public List<News> getList() throws NewsDAOException {
		List<News> newsList = new ArrayList<News>();

		String getNewsSQLRequest = "SELECT * FROM news";
		try (Connection con = ConnectionPool.getInstanceCP().takeConnection();
				PreparedStatement ps = con.prepareStatement(getNewsSQLRequest)) {

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				newsList.add(new News(rs.getInt(NewsParameter.ID_NEWS), rs.getString(NewsParameter.NEWS_TITLE),
						rs.getString(NewsParameter.NEWS_BRIEF), rs.getString(NewsParameter.NEWS_CONTENT),
						rs.getTimestamp(NewsParameter.NEWS_DATE)));
			}
			return newsList;

		} catch (SQLException | ConnectionPoolException e) {
			throw new NewsDAOException("Error all news getting", e);
		}
	}

	@Override
	public List<News> getLatestsList(int count) throws NewsDAOException {
		List<News> latestNews = new ArrayList<News>();

		String getLatestNewsSQLRequest = "SELECT * FROM news ORDER BY idnews DESC LIMIT ?";
		try (Connection con = ConnectionPool.getInstanceCP().takeConnection();
				PreparedStatement ps = con.prepareStatement(getLatestNewsSQLRequest)) {

			ps.setInt(1, count);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				latestNews.add(new News(rs.getInt(NewsParameter.ID_NEWS), rs.getString(NewsParameter.NEWS_TITLE),
						rs.getString(NewsParameter.NEWS_BRIEF), rs.getString(NewsParameter.NEWS_CONTENT),
						rs.getTimestamp(NewsParameter.NEWS_DATE)));
			}

			return latestNews;
		} catch (SQLException | ConnectionPoolException e) {
			throw new NewsDAOException("Error latest news getting", e);
		}
	}

	@Override
	public News fetchById(int id) throws NewsDAOException {
//		return new News(1, "title1", "brief1brief1brief1brief1brief1brief1brief1", "contect1", "11/11/22");
		return null;
	}

	@Override
	public int addNews(News news) throws NewsDAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateNews(News news) throws NewsDAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteNewses(String[] idNewses) throws NewsDAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<News> getNewsListForOnePage(int skip, int count) throws NewsDAOException {
		List<News> result = new ArrayList<News>();

		String getNewsForOnePageSQLRequest = "SELECT * FROM news ORDER BY idnews DESC LIMIT ?, ?";
		try (Connection con = ConnectionPool.getInstanceCP().takeConnection();
				PreparedStatement ps = con.prepareStatement(getNewsForOnePageSQLRequest)) {

			ps.setInt(1, skip);
			ps.setInt(2, count);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result.add(new News(rs.getInt(NewsParameter.ID_NEWS), rs.getString(NewsParameter.NEWS_TITLE),
						rs.getString(NewsParameter.NEWS_BRIEF), rs.getString(NewsParameter.NEWS_CONTENT),
						rs.getTimestamp(NewsParameter.NEWS_DATE)));
			}

			return result;
		} catch (SQLException | ConnectionPoolException e) {
			throw new NewsDAOException("Error one page news getting", e);
		}
	}

	@Override
	public int countOfNews() throws NewsDAOException {

		String getCountOfNewsSQLRequest = "SELECT COUNT(*) AS total FROM news";
		try (Connection con = ConnectionPool.getInstanceCP().takeConnection();
				PreparedStatement ps = con.prepareStatement(getCountOfNewsSQLRequest)) {
			
			ps.setString(1, NewsParameter.TOTAL_COUNT_OF_NEWS);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getInt(NewsParameter.TOTAL_COUNT_OF_NEWS);
		} catch (SQLException | ConnectionPoolException e) {
			throw new NewsDAOException("Error one page news getting", e);
		}

	}

}
