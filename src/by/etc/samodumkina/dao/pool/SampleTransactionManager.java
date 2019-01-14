package by.etc.samodumkina.dao.pool;

import java.sql.Connection;
import java.sql.SQLException;

import by.etc.samodumkina.dao.exception.DAOException;
import by.etc.samodumkina.dao.pool.exception.ConnectionPoolException;

public class SampleTransactionManager {
	private final static SampleTransactionManager instance = new SampleTransactionManager();
	
	private static ThreadLocal<Connection> local = new ThreadLocal<>();
	
	private SampleTransactionManager() {}
	
	public static SampleTransactionManager getInstance() {
		return instance;
	}
	
	public void startTransaction() throws ConnectionPoolException, SQLException {
		Connection connection = ConnectionPool.getInstance().takeConnection();
		connection.setAutoCommit(false);
		local.set(connection);
	}
	
	public Connection takeConnection() {
		return local.get();
	}
	
	public void commit() throws SQLException {
		Connection connection = local.get();
		if (connection != null) {
			connection.commit();
		}
	}
	
	public void rollback() throws DAOException {
		Connection connection = local.get();
		try {
			if (connection != null) {
				connection.rollback();
			} 
		}catch(SQLException e) {
			throw new DAOException(e);
		}
	}
	
	public void closeConnection() throws DAOException {
		Connection connection = local.get();
		try {
			if (connection != null) {
				connection.setAutoCommit(true);
				connection.close();
			} 
		} catch(SQLException e) {
			throw new DAOException(e);
		}
	}

}
