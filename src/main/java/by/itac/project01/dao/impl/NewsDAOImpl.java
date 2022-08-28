package by.itac.project01.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.itac.project01.bean.News;
import by.itac.project01.dao.NewsDAO;
import by.itac.project01.dao.NewsDAOException;
import by.itac.project01.dao.connection.ConnectionPool;
import by.itac.project01.dao.connection.ConnectionPoolException;
import by.itac.project01.util.NewsParameter;

public class NewsDAOImpl implements NewsDAO {
	private static final Logger log = LogManager.getRootLogger();

	@Override
	public List<News> latestsList(int count) throws NewsDAOException {
		List<News> latestNews = new ArrayList<News>();

		String getLatestNewsSQLRequest = "SELECT * FROM news ORDER BY idnews DESC LIMIT ?";
		try (Connection con = ConnectionPool.getInstanceCP().takeConnection();
				PreparedStatement ps = con.prepareStatement(getLatestNewsSQLRequest)) {

			ps.setInt(1, count);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				latestNews
						.add(new News(rs.getInt(NewsParameter.ID_NEWS_COLUMN), rs.getString(NewsParameter.TITLE_COLUMN),
								rs.getString(NewsParameter.BRIEF_COLUMN), rs.getString(NewsParameter.CONTENT_COLUMN),
								rs.getDate(NewsParameter.DATE_COLUMN).toLocalDate()));
			}

			return latestNews;
		} catch (SQLException | ConnectionPoolException e) {
			throw new NewsDAOException("Error latest news getting", e);
		}
	}

	@Override
	public List<News> newsListForOnePage(int skip, int count) throws NewsDAOException {
		List<News> result = new ArrayList<News>();

		String getNewsForOnePageSQLRequest = "SELECT * FROM news ORDER BY idnews DESC LIMIT ?, ?";
		try (Connection con = ConnectionPool.getInstanceCP().takeConnection();
				PreparedStatement ps = con.prepareStatement(getNewsForOnePageSQLRequest)) {

			ps.setInt(1, skip);
			ps.setInt(2, count);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result.add(new News(rs.getInt(NewsParameter.ID_NEWS_COLUMN), rs.getString(NewsParameter.TITLE_COLUMN),
						rs.getString(NewsParameter.BRIEF_COLUMN), rs.getString(NewsParameter.CONTENT_COLUMN),
						rs.getDate(NewsParameter.DATE_COLUMN).toLocalDate()));
			}

			return result;
		} catch (SQLException | ConnectionPoolException e) {
			throw new NewsDAOException("Error one page news getting", e);
		}
	}

	@Override
	public int countOfNews() throws NewsDAOException {

		String getCountOfNewsSQLRequest = "SELECT COUNT(*) AS ? FROM news";
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

	@Override
	public int addNews(News news) throws NewsDAOException {
		String addNewsSQLRequest = "INSERT INTO news(title, brief, content, date) VALUES(?,?,?,?)";
		String getNewsIDSQLRequest = "SELECT LAST_INSERT_ID() FROM news";
		try (Connection con = ConnectionPool.getInstanceCP().takeConnection();
				PreparedStatement psInsert = con.prepareStatement(addNewsSQLRequest);
				PreparedStatement psSelect = con.prepareStatement(getNewsIDSQLRequest)) {

			psInsert.setString(1, news.getTitle());
			psInsert.setString(2, news.getBriefNews());
			psInsert.setString(3, news.getContent());
			psInsert.setDate(4, Date.valueOf(news.getNewsDate()));
			psInsert.executeUpdate();

			ResultSet rs = psSelect.executeQuery();
			rs.next();

			return rs.getInt(1);

		} catch (SQLException | ConnectionPoolException e) {
			log.log(Level.ERROR, "Saving news failed", e);
			throw new NewsDAOException("Saving news failed", e);
		}

	}

	@Override
	public News findById(int idNews) throws NewsDAOException {

		String findNewsByIDSQLRequest = "SELECT * FROM news WHERE idnews=?";
		try (Connection con = ConnectionPool.getInstanceCP().takeConnection();
				PreparedStatement ps = con.prepareStatement(findNewsByIDSQLRequest)) {

			ps.setInt(1, idNews);
			ResultSet rs = ps.executeQuery();

			rs.next();

			return new News(rs.getInt(NewsParameter.ID_NEWS_COLUMN), rs.getString(NewsParameter.TITLE_COLUMN),
					rs.getString(NewsParameter.BRIEF_COLUMN), rs.getString(NewsParameter.CONTENT_COLUMN),
					rs.getDate(NewsParameter.DATE_COLUMN).toLocalDate());

		} catch (SQLException | ConnectionPoolException e) {
			log.log(Level.ERROR, "Finding news failed", e);
			throw new NewsDAOException("Finding news failed", e);
		}
	}

	@Override
	public void updateNews(News news) throws NewsDAOException {
		String updateNewsByIDSQLRequest = "UPDATE news SET title=?, brief=?, content=?, date=? WHERE idnews=?";
		try (Connection con = ConnectionPool.getInstanceCP().takeConnection();
				PreparedStatement ps = con.prepareStatement(updateNewsByIDSQLRequest)) {

			ps.setString(1, news.getTitle());
			ps.setString(2, news.getBriefNews());
			ps.setString(3, news.getContent());
			ps.setDate(4, Date.valueOf(news.getNewsDate()));
			ps.setInt(5, news.getIdNews());

			ps.executeUpdate();

		} catch (SQLException | ConnectionPoolException e) {
			log.log(Level.ERROR, "Editing news failed", e);
			throw new NewsDAOException("Editing news failed", e);
		}

	}

	@Override
	public void deleteNews(int[] idNews) throws NewsDAOException {
		String deleteNewsByIDSQLRequest = "DELETE FROM news WHERE idnews=?";
		try (Connection con = ConnectionPool.getInstanceCP().takeConnection();
				PreparedStatement ps = con.prepareStatement(deleteNewsByIDSQLRequest)) {

			for (int id : idNews) {
				ps.setInt(1, id);
				ps.executeUpdate();
			}

		} catch (SQLException | ConnectionPoolException e) {
			log.log(Level.ERROR, "Deleting news failed", e);
			throw new NewsDAOException("Deleting news failed", e);
		}
	}

}
