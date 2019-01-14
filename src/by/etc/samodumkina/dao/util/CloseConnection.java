package by.etc.samodumkina.dao.util;

import java.sql.Connection;
import java.sql.SQLException;

import by.etc.samodumkina.dao.exception.DAOException;

public class CloseConnection {
	private final static CloseConnection instance = new CloseConnection();

	private CloseConnection() {}
	
	public static CloseConnection getInstance() {
		return instance;
	}
	
	public void close(Connection connection) throws DAOException {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

}
