package by.etc.samodumkina.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import by.etc.samodumkina.bean.Order;
import by.etc.samodumkina.dao.TakeInfoDAO;
import by.etc.samodumkina.dao.exception.DAOException;
import by.etc.samodumkina.dao.pool.ConnectionPool;
import by.etc.samodumkina.dao.pool.exception.ConnectionPoolException;
import by.etc.samodumkina.dao.util.CloseResultSet;
import by.etc.samodumkina.dao.util.CloseStatement;
import by.etc.samodumkina.specification.Specification;

/**
 * 
 * DAO class to read closed and not closed orders from database.
 * Depends of mysql specification will read all orders of all users or all orders from one concrete user
 * 
 * @return list of orders
 *
 */

public class SQLReadOrderStory implements TakeInfoDAO<Order>{
	private final static String ID = "orderId";
	private final static String LOGIN = "login";
	private final static String BOOK_NAME = "bookName";
	private final static String BOOK_AUTHORS = "bookAuthors";
	private final static String DATE = "date";
	private final static String NEW_DATE = "new_date";
	private final static String IS_REPLACE = "isReplace";
	
	private final static int BEGIN_INDEX = 0;
	private final static int END_INDEX = 10;
	

	@Override
	public List<Order> read(Specification specification) throws DAOException {
		Statement statement = null;
		ResultSet resultSet = null;
		
		List<Order> result = new LinkedList<>();
		
		try(Connection connection = ConnectionPool.getInstance().takeConnection()){
			
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery(specification.toQuery());
			
			while (resultSet.next()) {
				Order order = new Order();
				
				order.setId(resultSet.getString(ID));
				order.setUserName(resultSet.getString(LOGIN));
				order.setBookName(resultSet.getString(BOOK_NAME));
				order.setBookAuthors(resultSet.getString(BOOK_AUTHORS));
				order.setOrderDate(resultSet.getDate(DATE).toLocaleString().substring(BEGIN_INDEX, END_INDEX));
				order.setDateOfExpiry(resultSet.getDate(NEW_DATE).toLocaleString().substring(BEGIN_INDEX, END_INDEX));
				order.setIsReplace(resultSet.getString(IS_REPLACE));
				
				result.add(order);
			}
			
		} catch (SQLException e) {
			throw new DAOException("sql exception while reading order story from database", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("cannot create exception due to problems with connection pool", e);
		} finally {
			CloseResultSet.getInstance().close(resultSet);
			CloseStatement.getInstance().close(statement);
		}
		
		return result;
	}

}
