package by.etc.samodumkina.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import by.etc.samodumkina.bean.NeedSendOutBook;
import by.etc.samodumkina.dao.TakeInfoDAO;
import by.etc.samodumkina.dao.exception.DAOException;
import by.etc.samodumkina.dao.pool.ConnectionPool;
import by.etc.samodumkina.dao.pool.exception.ConnectionPoolException;
import by.etc.samodumkina.dao.util.CloseResultSet;
import by.etc.samodumkina.dao.util.CloseStatement;
import by.etc.samodumkina.specification.Specification;

/**
 * 
 * DAO class read queued books (preorders) from 'needsendoutbooks' table in database.
 * Depends of mysql specification will read preorders to home or to reading room.
 * 
 * @return list of queued books (preorders)
 *
 */

public class SQLReadNeedSendOutBook implements TakeInfoDAO<NeedSendOutBook>{
	
	private final static String LOGIN = "login";
	private final static String BOOK_NAME = "bookName";
	private final static String BOOK_AUTHORS = "bookAuthors";
	private final static String ID = "needsendoutbooks.id";

	@Override
	public List<NeedSendOutBook> read(Specification specification) throws DAOException {
		ResultSet resultSet = null;
		Statement statement = null;
		List<NeedSendOutBook> result = new LinkedList<>();
		
		try(Connection connection = ConnectionPool.getInstance().takeConnection()){
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery(specification.toQuery());
			
			while(resultSet.next()) {
				NeedSendOutBook book = new NeedSendOutBook();
				
				book.setId(resultSet.getString(ID));
				book.setUserName(resultSet.getString(LOGIN));
				book.setBookName(resultSet.getString(BOOK_NAME));
				book.setBookAuthors(resultSet.getString(BOOK_AUTHORS));
				
				result.add(book);
			}
			
		} catch (SQLException e) {
			throw new DAOException("sql exception while reading need send out books from database", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("cannot create exception due to problems with connection pool", e);
		} finally {
			CloseResultSet.getInstance().close(resultSet);
			CloseStatement.getInstance().close(statement);
		}
		
		return result;
	}

}
