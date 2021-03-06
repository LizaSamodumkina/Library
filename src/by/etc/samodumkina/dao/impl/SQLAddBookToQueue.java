package by.etc.samodumkina.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import by.etc.samodumkina.bean.PreOrder;
import by.etc.samodumkina.dao.AddInfoDAO;
import by.etc.samodumkina.dao.exception.DAOException;
import by.etc.samodumkina.dao.pool.SampleTransactionManager;
import by.etc.samodumkina.dao.pool.exception.ConnectionPoolException;
import by.etc.samodumkina.dao.util.CloseResultSet;
import by.etc.samodumkina.dao.util.CloseStatement;

/**
 * 
 * DAO class that with transaction check is user has opportunity to order book 
 * and if yes, put order information to order queue in database and return true.
 * If user doesn't have such opportunity order will not be queued and function add() returns false
 *
 * @return true, if order was queued. false, if it's not.
 */

public class SQLAddBookToQueue implements AddInfoDAO<PreOrder>{
	private final static String SELECT_USER_ORDERS_NUM = "select count(*) as number from bookOrderStory where userId = (select user_id from users where login = ?) and isReplace = 0";
	private final static String SELECT_USER_OLD_NOT_REPLACED_ORDERS = "select count(*) as number from bookorderstory where (adddate(date, 30) < CURDATE()) and isReplace = 0 and userId = (select user_id from users where login = ?)";
	private final static String SELECT = "select availableBookNumber from books where id = (select bookIdLB from userlikedbooks where id = ?)";
	private final static String INSERT_USER_TOBLOCKE_LIST = "insert into blockedusers (blockedUserId) values ((select user_id from users where login = ?))";
	private final static String INSERT = "insert into needsendoutbooks (userIdNS, bookIdNS, isinreadingroom) values ((select user_id from users where login = ?), (select bookIdLB from userlikedbooks where id = ?), ?)";
	private final static String DELETE_FROM_LIKED = "delete from userlikedbooks where id = ?";
	private final static String UPDATE_AVAILABLE_BOOK_NUM = "update books set availableBookNumber = (select availableBookNumber - 1 where id = (select bookIdLB from userlikedbooks where id = ?)) where id = (select bookIdLB from userlikedbooks where id = ?)";
	
	private final static int FIRST = 1;
	private final static int SECOND = 2;
	private final static int THIRD = 3;
	private final static int PREORDER_INDEX = 0;
	
	private final static String AVAILABLE_BOOK_NUMBER = "availableBookNumber";
	private final static String NOT_REPLACED_ORDERS_NUMBER = "number";
	private final static String OLD_DO_NOT_REPLACED_ORDERS_NUMBER = "number";
	
	private final static int AVAILABLE_NUMBER_FOR_NOT_REPLACED_ORDERS = 5;
	private final static int SMALLEST_NUMBER_FOR_AVAILABLE_BOOK = 0;
	private final static int AVAILABLE_NUMBER_FOR_OLD_NOT_REPLACED_ORDERS = 1;
	
	@Override
	public boolean add(List<PreOrder> data) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try{
			SampleTransactionManager.getInstance().startTransaction();
			connection = SampleTransactionManager.getInstance().takeConnection();
			
			if (!isUserDoNotBlocked(statement, resultSet, connection, data.get(PREORDER_INDEX).getUser().getLogin())){
				
				addUserToBlockeList(statement, connection, data.get(PREORDER_INDEX).getUser().getLogin());
				return false;
			}
			
			if (!isUserHasNormalOrderNumber(statement, resultSet, connection, data.get(PREORDER_INDEX).getUser().getLogin())) {
				
				return false;
			}
			
			if (!isHasAvailableBook(statement, resultSet, connection, data.get(PREORDER_INDEX).getLikedBookId())) {
				
				return false;
			}
			insertIntoQueue(statement, connection, data);
			updateAvailableBookNum(statement, connection, data.get(PREORDER_INDEX).getLikedBookId());
			deleteFromLiked(statement, connection, data.get(PREORDER_INDEX).getLikedBookId());
			
			SampleTransactionManager.getInstance().commit();
		} catch (SQLException e) {
			SampleTransactionManager.getInstance().rollback();
			throw new DAOException("cannot add book to queue", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("cannot add book to queue due to problems with connection pool", e);
		} finally {
			 CloseResultSet.getInstance().close(resultSet);
			 CloseStatement.getInstance().close(statement);
			 SampleTransactionManager.getInstance().closeConnection();
		}
		
		return true;
		
	}

	
	private boolean isUserHasNormalOrderNumber(PreparedStatement statement, ResultSet resultSet, Connection connection, String login) throws SQLException {
		statement = connection.prepareStatement(SELECT_USER_ORDERS_NUM);
		
		statement.setString(FIRST, login);
		
		resultSet = statement.executeQuery();
		
		boolean answer = false;
		while(resultSet.next()) {
			if (resultSet.getInt(NOT_REPLACED_ORDERS_NUMBER) < AVAILABLE_NUMBER_FOR_NOT_REPLACED_ORDERS) {
				answer = true;
			}
		}
		
		return answer;
	}
	
	private boolean isUserDoNotBlocked(PreparedStatement statement, ResultSet resultSet, Connection connection, String login) throws SQLException {
		statement = connection.prepareStatement(SELECT_USER_OLD_NOT_REPLACED_ORDERS);
		
		statement.setString(FIRST, login);
		
		resultSet = statement.executeQuery();
		
		boolean answer = false;
		
		while(resultSet.next()) {
			if (resultSet.getInt(OLD_DO_NOT_REPLACED_ORDERS_NUMBER) <= AVAILABLE_NUMBER_FOR_OLD_NOT_REPLACED_ORDERS) {
				answer = true;
			}
		}
		
		return answer;
	}
	
	private void addUserToBlockeList(PreparedStatement statement, Connection connection, String login) throws SQLException {
		statement = connection.prepareStatement(INSERT_USER_TOBLOCKE_LIST);
		
		statement.setString(FIRST, login);
		
		statement.execute();
	}
	
	private boolean isHasAvailableBook(PreparedStatement statement, ResultSet resultSet, Connection connection, String bookId) throws SQLException {
		statement = connection.prepareStatement(SELECT);
		
		statement.setString(FIRST, bookId);
		
		resultSet = statement.executeQuery();
		
		boolean answer = false;
		while(resultSet.next()) {
			if (resultSet.getInt(AVAILABLE_BOOK_NUMBER) > SMALLEST_NUMBER_FOR_AVAILABLE_BOOK) {
				answer = true;
			}
		}
		
		return answer;
	}

	private void insertIntoQueue(PreparedStatement statement, Connection connection, List<PreOrder> data) throws SQLException {
		statement = connection.prepareStatement(INSERT);
		
		statement.setString(FIRST, data.get(PREORDER_INDEX).getUser().getLogin());
		statement.setString(SECOND, data.get(PREORDER_INDEX).getLikedBookId());
		
		byte isInReadingRoom = 1;
		if (data.get(PREORDER_INDEX).isOrderToHome()) {
			isInReadingRoom = 0;
		}
		
		statement.setInt(THIRD, isInReadingRoom);
		
		statement.execute();
	}
	
	private void deleteFromLiked(PreparedStatement statement, Connection connection, String bookId) throws SQLException {
		statement = connection.prepareStatement(DELETE_FROM_LIKED);
		
		statement.setString(FIRST, bookId);
		
		statement.execute();
	}

	private void updateAvailableBookNum(PreparedStatement statement, Connection connection, String bookId) throws SQLException {
		statement = connection.prepareStatement(UPDATE_AVAILABLE_BOOK_NUM);
		
		statement.setInt(FIRST, Integer.parseInt(bookId));
		statement.setInt(SECOND, Integer.parseInt(bookId));
		
		statement.executeUpdate();
	}

}
