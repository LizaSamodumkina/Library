package by.etc.samodumkina.dao.util;

import java.sql.SQLException;
import java.sql.Statement;

import by.etc.samodumkina.dao.exception.DAOException;

public class CloseStatement {
	private final static CloseStatement instance = new CloseStatement();
	
	private CloseStatement() {}
	
	public static CloseStatement getInstance() {
		return instance;
	}
	
	public void close(Statement statement) throws DAOException {
		try {
			if (statement != null) {
				statement.close();
			}
		}catch(SQLException e) {
			throw new DAOException("cannot close statement", e);
		}
	}
}
