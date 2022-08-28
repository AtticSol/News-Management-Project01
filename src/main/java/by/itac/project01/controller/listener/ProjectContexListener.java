package by.itac.project01.controller.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.itac.project01.dao.connection.ConnectionPool;
import by.itac.project01.dao.connection.ConnectionPoolException;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class ProjectContexListener implements ServletContextListener{
	
	private static final Logger log = LogManager.getRootLogger();
	
	public ProjectContexListener() {}
	
	public void contexInitialized(ServletContextEvent e) {
		try {
			ConnectionPool.getInstanceCP();
		} catch (ConnectionPoolException ex) {
			log.error("ConnectionPool is not initialized", ex);
			throw new RuntimeException();
		}
	}
	
	public void contexDestroyed(ServletContextEvent e) {
		try {
		ConnectionPool.getInstanceCP().dispose();
		} catch (ConnectionPoolException ex) {
			log.error("ConnectionPool is not destroyed", ex);
			throw new RuntimeException();
		}
	}

}
