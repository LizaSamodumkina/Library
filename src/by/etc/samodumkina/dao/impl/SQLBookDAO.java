package by.etc.samodumkina.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import by.etc.samodumkina.bean.Book;
import by.etc.samodumkina.dao.BookDAO;
import by.etc.samodumkina.dao.exception.DAOException;
import by.etc.samodumkina.dao.pool.ConnectionPool;
import by.etc.samodumkina.dao.pool.exception.ConnectionPoolException;
import by.etc.samodumkina.dao.util.CloseStatement;

/**
 * 
 * DAO class that give opportunity to put new book into system (with information of copy number), or delete already exist book.
 *
 */

public class SQLBookDAO implements BookDAO{
	
	private final static String INSERT_NEW_BOOK = "insert into books (bookName, bookAuthors, annotation, description, bookNumber, availableBookNumber) values (?, ?, ?, ?, ?, ?)";
	private final static int FIRST = 1;
	private final static int SECOND = 2;
	private final static int THIRD = 3;
	private final static int FORTH = 4;
	private final static int FIFTH = 5;
	private final static int SIXTH = 6;
	
	@Override
	public void addBook(Book book) throws DAOException{
		PreparedStatement statement = null;
		
		try(Connection connection = ConnectionPool.getInstance().takeConnection()){
			
			statement = connection.prepareStatement(INSERT_NEW_BOOK);
			statement.setString(FIRST, book.getName());
			statement.setString(SECOND, book.getAuthors());
			statement.setString(THIRD, book.getAnnotation());
			statement.setString(FORTH, book.getDescription());
			statement.setInt(FIFTH, book.getColInLabrary());
			statement.setInt(SIXTH, book.getColInLabrary());
			
			statement.execute();
			
		} catch (SQLException e) {
			throw new DAOException("sql exception while adding a new book to database", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("cannot create exception due to problems with connection pool", e);
		} finally {
			CloseStatement.getInstance().close(statement);
		}
		
	}

	@Override
	public void deleteBook(String bookId) throws DAOException{

	}

}
