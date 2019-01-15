package by.etc.samodumkina.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import by.etc.samodumkina.bean.User;
import by.etc.samodumkina.dao.TakeInfoDAO;
import by.etc.samodumkina.dao.exception.DAOException;
import by.etc.samodumkina.dao.pool.ConnectionPool;
import by.etc.samodumkina.dao.pool.exception.ConnectionPoolException;
import by.etc.samodumkina.dao.util.CloseResultSet;
import by.etc.samodumkina.dao.util.CloseStatement;
import by.etc.samodumkina.specification.Specification;

/**
 * 
 * DAO class to read already blocked users from 'blokedusers' table in database by mysql specification.
 *
 */

public class SQLReadBlockedUsers implements TakeInfoDAO<User> {
	
	private final static String LOGIN = "login";

	@Override
	public List<User> read(Specification specification) throws DAOException {
		List<User> users = new LinkedList<>();
		
		Statement statement = null;
		ResultSet resultSet = null;
		
		try(Connection connection = ConnectionPool.getInstance().takeConnection()){
			
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery(specification.toQuery());
			while(resultSet.next()){
				User user = new User();
				user.setLogin(resultSet.getString(LOGIN));
				users.add(user);
			}
			
		} catch (SQLException e) {
			throw new DAOException("sql exception while reading bloked users from database", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("cannot create exception due to problems with connection pool", e);
		} finally {
			CloseResultSet.getInstance().close(resultSet);
			CloseStatement.getInstance().close(statement);
		}
		
		return users;
	}

}
