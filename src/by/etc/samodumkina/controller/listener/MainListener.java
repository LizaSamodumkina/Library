package by.etc.samodumkina.controller.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.etc.samodumkina.dao.pool.ConnectionPool;
import by.etc.samodumkina.dao.pool.exception.ConnectionPoolException;
import by.etc.samodumkina.service.impl.SendNotificationToUser;

public class MainListener implements ServletContextListener {
	
	private final static Logger log = LogManager.getLogger(MainListener.class);

    public MainListener() {}

    public void contextDestroyed(ServletContextEvent sce)  { 
    	try {
			ConnectionPool.getInstance().closePool();
		} catch (ConnectionPoolException e) {
			log.error(e.getStackTrace());
		}
    }

    public void contextInitialized(ServletContextEvent sce)  { 
        Thread thread = new SendNotificationToUser();
        thread.setDaemon(true);
        thread.start();
    }
	
}
