package by.etc.samodumkina.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import by.etc.samodumkina.dao.AddInfoDAO;
import by.etc.samodumkina.dao.exception.DAOException;
import by.etc.samodumkina.dao.pool.ConnectionPool;
import by.etc.samodumkina.dao.pool.exception.ConnectionPoolException;
import by.etc.samodumkina.dao.util.CloseStatement;

public class SQLAddUserLikedBook implements AddInfoDAO<String>{
	private final static String INSERT = "insert into userlikedbooks (userIdLB, bookIdLB)  values ((select user_id from users where login = ?), ?)";
	private final static int FIRST = 1;
	private final static int SECOND = 2;
	private final static int USER_LOGIN_INDEX = 0;
	private final static int BOOK_ID_INDEX = 1;

	@Override
	public boolean add(List<String> data) throws DAOException {
		PreparedStatement statement = null;
		
		try(Connection connection = ConnectionPool.getInstance().takeConnection()){
			
			statement = connection.prepareStatement(INSERT);
			statement.setString(FIRST, data.get(USER_LOGIN_INDEX));
			statement.setString(SECOND, data.get(BOOK_ID_INDEX));
			
			statement.execute();
			
		} catch (SQLException e) {
			throw new DAOException("sql exception while adding user liked book from database", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("cannot create exception due to problems with connection pool", e);
		} finally {
			CloseStatement.getInstance().close(statement);
		}
		
		return true;
	}
}
