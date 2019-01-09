package by.etc.samodumkina.dao.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;

import by.etc.samodumkina.dao.exception.DAOException;

public class RollBackTransaction {
	private final static RollBackTransaction instance = new RollBackTransaction();

	private RollBackTransaction() {}
	
	public static RollBackTransaction getInstance() {
		return instance;
	}

	public void rollBack(Connection con, Savepoint point) throws DAOException {
		try {
			if (con != null && point != null) {
				con.rollback(point);
			}
		} catch (SQLException e) {
			throw new DAOException("cannot rollback transaction", e);
		}
	}
}
