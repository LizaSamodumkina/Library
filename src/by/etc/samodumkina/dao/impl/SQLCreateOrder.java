package by.etc.samodumkina.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.List;

import by.etc.samodumkina.dao.AddInfoDAO;
import by.etc.samodumkina.dao.exception.DAOException;
import by.etc.samodumkina.dao.pool.ConnectionPool;
import by.etc.samodumkina.dao.pool.exception.ConnectionPoolException;
import by.etc.samodumkina.dao.util.CloseConnection;
import by.etc.samodumkina.dao.util.CloseStatement;
import by.etc.samodumkina.dao.util.RollBackTransaction;

public class SQLCreateOrder implements AddInfoDAO<String> {
	private final static int HOME_INDEX = 1;
	private final static int ID_INDEX = 0;
	private final static int FIRST = 1;
	private final static int SECOND = 2;
	private final static String TRUE = "true";
	private final static String POINT_NAME = "point";
	
	private final static String INSERT_NEW_ORDER = "insert into takenbooks (userIdTB, bookIdTB, date) values((select userIdNS from needsendoutbooks where id = ?), (select bookIdNS from needsendoutbooks where id = ?), (SELECT CURDATE()))";
	private final static String INSERT_TO_BOOK_ORDER_STORY = "insert into bookorderstory (userId, bookId, date, isReplace) values ((select userIdNS from needsendoutbooks where id = ?), (select bookIdNS from needsendoutbooks where id = ?), (SELECT CURDATE()), 0)";
	private final static String INSER_NEW_ORDER_TO_READING_ROOM = "insert into readingroombooks (userIdRR, bookIdRR, date) values((select userIdNS from needsendoutbooks where id = ?), (select bookIdNS from needsendoutbooks where id = ?), (SELECT CURDATE())) ";
	private final static String DELETE_FROM_QUEUE = "delete from needsendoutbooks where id = ?";

	private Connection connection;
	private PreparedStatement statement;
	
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
		Savepoint point = null;
		
		try{
			connection = ConnectionPool.getInstance().takeConnection();
			connection.setAutoCommit(false);
			
			point = connection.setSavepoint(POINT_NAME);
			
			insertIntoTakenBooks(id);
			insertIntoBookOrderStory(id);
			deleteOrderFromQueue(id);
			
			connection.commit();
		} catch (SQLException e) {
			RollBackTransaction.getInstance().rollBack(connection, point);
			throw new DAOException("cannot create new order to home", e);
		} catch (ConnectionPoolException e) {
			RollBackTransaction.getInstance().rollBack(connection, point);
			throw new DAOException("cannot create new order to home due to problems with connection pool", e);
		} finally {
			CloseStatement.getInstance().close(statement);
			CloseConnection.getInstance().close(connection);
		}
	}
	
	private void makeOrderToReadingRoom(String id) throws DAOException {
		Savepoint point = null;
		
		try{
			connection = ConnectionPool.getInstance().takeConnection();
			connection.setAutoCommit(false);
			
			insertIntoTakenToReadingRoomBooks(id);
			insertIntoBookOrderStory(id);
			deleteOrderFromQueue(id);
			
			point = connection.setSavepoint(POINT_NAME);
			
			connection.commit();
		} catch (SQLException e) {
			RollBackTransaction.getInstance().rollBack(connection, point);
			throw new DAOException("cannot create new order to home", e);
		} catch (ConnectionPoolException e) {
			RollBackTransaction.getInstance().rollBack(connection, point);
			throw new DAOException("cannot create new order to home due to problems with connection pool", e);
		} finally {
			CloseStatement.getInstance().close(statement);
			CloseConnection.getInstance().close(connection);
		}
	}
	
	private void insertIntoBookOrderStory(String id) throws SQLException {
		
		statement = connection.prepareStatement(INSERT_TO_BOOK_ORDER_STORY);
		statement.setString(FIRST, id);
		statement.setString(SECOND, id);
			
		statement.execute();
	}
	
	private void insertIntoTakenBooks(String id) throws SQLException{
			
		statement = connection.prepareStatement(INSERT_NEW_ORDER);
		statement.setString(FIRST, id);
		statement.setString(SECOND, id);
			
		statement.execute();
	}
	
	private void insertIntoTakenToReadingRoomBooks(String id) throws SQLException {
			
		statement = connection.prepareStatement(INSER_NEW_ORDER_TO_READING_ROOM);
		statement.setString(FIRST, id);
		statement.setString(SECOND, id);
			
		statement.execute();
	}

	private void deleteOrderFromQueue(String id) throws SQLException {
		statement = connection.prepareStatement(DELETE_FROM_QUEUE);
		statement.setString(FIRST, id);
		
		statement.execute();
	}
}
