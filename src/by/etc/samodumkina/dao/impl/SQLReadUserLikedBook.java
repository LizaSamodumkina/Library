package by.etc.samodumkina.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import by.etc.samodumkina.bean.Book;
import by.etc.samodumkina.dao.TakeInfoDAO;
import by.etc.samodumkina.dao.exception.DAOException;
import by.etc.samodumkina.dao.pool.ConnectionPool;
import by.etc.samodumkina.dao.pool.exception.ConnectionPoolException;
import by.etc.samodumkina.dao.util.CloseResultSet;
import by.etc.samodumkina.dao.util.CloseStatement;
import by.etc.samodumkina.specification.Specification;

public class SQLReadUserLikedBook implements TakeInfoDAO<Book> {

	private final static String BOOK_ID = "id";
	private final static String BOOK_NAME = "bookName";
	private final static String BOOK_AUTHORS = "bookAuthors";
	private final static String ANNOTATION = "annotation";
	
	public SQLReadUserLikedBook() {}
	
	@Override
	public List<Book> read(Specification specification) throws DAOException {
		List<Book> result = new LinkedList<>();
		
		Statement statement = null;
		ResultSet resultSet = null;
		
		try(Connection connection = ConnectionPool.getInstance().takeConnection()){
			statement = connection.createStatement();			
			resultSet = statement.executeQuery(specification.toQuery());
			
			while(resultSet.next()) {
				Book book = new Book();
				book.setId(resultSet.getInt(BOOK_ID));
				book.setName(resultSet.getString(BOOK_NAME));
				book.setAuthors(resultSet.getString(BOOK_AUTHORS));
				book.setAnnotation(resultSet.getString(ANNOTATION));
				result.add(book);
			}
			
		} catch (SQLException e) {
			throw new DAOException("sql exception while reading books from database", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("cannot create exception due to problems with connection pool", e);
		} finally {
			CloseResultSet.getInstance().close(resultSet);
			CloseStatement.getInstance().close(statement);
		}
		
		return result;
	}
}