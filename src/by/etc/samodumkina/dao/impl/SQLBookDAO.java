package by.etc.samodumkina.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import by.etc.samodumkina.bean.Book;
import by.etc.samodumkina.dao.BookDAO;
import by.etc.samodumkina.dao.exception.DAOException;
import by.etc.samodumkina.dao.pool.ConnectionPool;
import by.etc.samodumkina.dao.pool.exception.ConnectionPoolException;

public class SQLBookDAO implements BookDAO{
	private final static String SELECT = "select * from books";
	
	private final static String BOOK_NAME = "bookName";
	private final static String BOOK_AUTHORS = "bookAuthors";
	private final static String ANNOTATION = "annotation";
	private final static String DESCRIPTION = "description";
	private final static String BOOK_NUM = "bookNumber";

	public SQLBookDAO() {}

	@Override
	public List<Book> read() throws DAOException{
		List<Book> books = new LinkedList<>();
		
		Statement statement = null;
		ResultSet result = null;
		
		try(Connection connection = ConnectionPool.getInstance().takeConnection()){
			
			statement = connection.createStatement();
			result = statement.executeQuery(SELECT);
			
			while (result.next()) {
				Book book = new Book();
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
			try {
				if (result != null) {
					result.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				throw new DAOException("cannot close resultset or statement", e);
			}
		}
		
		return books;
	}

}
