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

/**
 * 
 * DAO class for reading all books from 'books' table in database by mysql specification
 *
 */

public class SQLReadBook implements TakeInfoDAO<Book>{	
	private final static String BOOK_NAME = "bookName";
	private final static String BOOK_AUTHORS = "bookAuthors";
	private final static String ANNOTATION = "annotation";
	private final static String DESCRIPTION = "description";
	private final static String BOOK_NUM = "bookNumber";
	private final static String BOOK_ID = "id";

	public SQLReadBook() {}

	@Override
	public List<Book> read(Specification specification) throws DAOException{
		List<Book> books = new LinkedList<>();
		
		Statement statement = null;
		ResultSet result = null;
		
		try(Connection connection = ConnectionPool.getInstance().takeConnection()){
			
			statement = connection.createStatement();
			result = statement.executeQuery(specification.toQuery());
			
			while (result.next()) {
				Book book = new Book();
				book.setId(result.getInt(BOOK_ID));
				book.setName(result.getString(BOOK_NAME));
				book.setAuthors(result.getString(BOOK_AUTHORS));
				book.setAnnotation(result.getString(ANNOTATION));
				book.setDescription(result.getString(DESCRIPTION));
				book.setColInLabrary(result.getInt(BOOK_NUM));
				books.add(book);
			}
			
		} catch (SQLException e) {
			throw new DAOException("sql exception while reading books from database", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("cannot create exception due to problems with connection pool", e);
		} finally {
			CloseResultSet.getInstance().close(result);
			CloseStatement.getInstance().close(statement);
		}
		
		return books;
	}
}
