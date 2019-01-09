package by.etc.samodumkina.dao.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.etc.samodumkina.dao.exception.DAOException;

public class CloseResultSet {
	private final static CloseResultSet instance = new CloseResultSet();
	
	private CloseResultSet() {}
	
	public static CloseResultSet getInstance() {
		return instance;
	}

	public void close(ResultSet result) throws DAOException {
		try {
			if (result != null) {
				result.close();
			}
		}catch(SQLException e) {
			throw new DAOException("cannot close resultset", e);
		}
	}
}
