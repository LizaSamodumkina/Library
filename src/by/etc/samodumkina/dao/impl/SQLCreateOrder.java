package by.etc.samodumkina.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import by.etc.samodumkina.dao.AddInfoDAO;
import by.etc.samodumkina.dao.exception.DAOException;
import by.etc.samodumkina.dao.pool.SampleTransactionManager;
import by.etc.samodumkina.dao.pool.exception.ConnectionPoolException;
import by.etc.samodumkina.dao.util.CloseStatement;

public class SQLCreateOrder implements AddInfoDAO<String> {
	private final static int HOME_INDEX = 1;
	private final static int ID_INDEX = 0;
	private final static int FIRST = 1;
	private final static int SECOND = 2;
	private final static String TRUE = "true";
	
	private final static String INSERT_NEW_ORDER = "insert into takenbooks (userIdTB, bookIdTB, date) values((select userIdNS from needsendoutbooks where id = ?), (select bookIdNS from needsendoutbooks where id = ?), (SELECT CURDATE()))";
	private final static String INSERT_TO_BOOK_ORDER_STORY = "insert into bookorderstory (userId, bookId, date, isReplace) values ((select userIdNS from needsendoutbooks where id = ?), (select bookIdNS from needsendoutbooks where id = ?), (SELECT CURDATE()), 0)";
	private final static String INSER_NEW_ORDER_TO_READING_ROOM = "insert into readingroombooks (userIdRR, bookIdRR, date) values((select userIdNS from needsendoutbooks where id = ?), (select bookIdNS from needsendoutbooks where id = ?), (SELECT CURDATE())) ";
	private final static String DELETE_FROM_QUEUE = "delete from needsendoutbooks where id = ?";
	
	@Override
	public boolean add(List<String> data) throws DAOException {
		if (data.get(HOME_INDEX).equals(TRUE)) {
			makeOrderToHome(data.get(ID_INDEX));
		}else {
			makeOrderToReadingRoom(data.get(ID_INDEX));
		}
		
		return true;
	}
	
	private void makeOrderToHome(String id) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		
		try{
			SampleTransactionManager.getInstance().startTransaction();
			connection = SampleTransactionManager.getInstance().takeConnection();
			
			insertIntoTakenBooks(connection, statement, id);
			insertIntoBookOrderStory(connection, statement, id);
			deleteOrderFromQueue(connection, statement, id);
			
			SampleTransactionManager.getInstance().commit();
		} catch (SQLException e) {
			SampleTransactionManager.getInstance().rollback();
			throw new DAOException("cannot create new order to home", e);
		} catch (ConnectionPoolException e) {
			SampleTransactionManager.getInstance().rollback();
			throw new DAOException("cannot create new order to home due to problems with connection pool", e);
		} finally {
			CloseStatement.getInstance().close(statement);
			SampleTransactionManager.getInstance().closeConnection();
		}
	}
	
	private void makeOrderToReadingRoom(String id) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		
		try{
			SampleTransactionManager.getInstance().startTransaction();
			connection = SampleTransactionManager.getInstance().takeConnection();
			
			insertIntoTakenToReadingRoomBooks(connection, statement, id);
			insertIntoBookOrderStory(connection, statement, id);
			deleteOrderFromQueue(connection,statement, id);
			
			SampleTransactionManager.getInstance().commit();
		} catch (SQLException e) {
			SampleTransactionManager.getInstance().rollback();
			throw new DAOException("cannot create new order to home", e);
		} catch (ConnectionPoolException e) {
			SampleTransactionManager.getInstance().rollback();
			throw new DAOException("cannot create new order to home due to problems with connection pool", e);
		} finally {
			CloseStatement.getInstance().close(statement);
			SampleTransactionManager.getInstance().closeConnection();
		}
	}
	
	private void insertIntoBookOrderStory(Connection connection , PreparedStatement statement, String id) throws SQLException {
		
		statement = connection.prepareStatement(INSERT_TO_BOOK_ORDER_STORY);
		statement.setString(FIRST, id);
		statement.setString(SECOND, id);
			
		statement.execute();
	}
	
	private void insertIntoTakenBooks(Connection connection , PreparedStatement statement, String id) throws SQLException{
			
		statement = connection.prepareStatement(INSERT_NEW_ORDER);
		statement.setString(FIRST, id);
		statement.setString(SECOND, id);
			
		statement.execute();
	}
	
	private void insertIntoTakenToReadingRoomBooks(Connection connection , PreparedStatement statement, String id) throws SQLException {
			
		statement = connection.prepareStatement(INSER_NEW_ORDER_TO_READING_ROOM);
		statement.setString(FIRST, id);
		statement.setString(SECOND, id);
			
		statement.execute();
	}

	private void deleteOrderFromQueue(Connection connection , PreparedStatement statement, String id) throws SQLException {
		statement = connection.prepareStatement(DELETE_FROM_QUEUE);
		statement.setString(FIRST, id);
		
		statement.execute();
	}
}
