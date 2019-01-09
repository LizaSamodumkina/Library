package by.etc.samodumkina.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import by.etc.samodumkina.bean.User;
import by.etc.samodumkina.dao.UserDAO;
import by.etc.samodumkina.dao.exception.DAOException;
import by.etc.samodumkina.dao.pool.ConnectionPool;
import by.etc.samodumkina.dao.pool.exception.ConnectionPoolException;
import by.etc.samodumkina.dao.util.CloseResultSet;
import by.etc.samodumkina.dao.util.CloseStatement;

public class SQLUserDAO implements UserDAO{
	private final static String SIGN_IN = "select * from users where login = ? and password = ?";
	private final static String REGISTRATION  = "insert into users (login, password, e_mail) values(?, ?, ?)";
	private final static String SELECT_IS_ADMIN = "select * from users where login = ? and is_admin = 1";
	
	private final static int FIRST = 1;
	private final static int SECOND = 2;
	private final static int THIRD = 3;

	public SQLUserDAO() {}

	@Override
	public boolean signIn(User user) throws DAOException{
		PreparedStatement statement = null;
		ResultSet result = null;
		
		boolean answer = false;
		try (Connection connection = ConnectionPool.getInstance().takeConnection()){
			
			statement = connection.prepareStatement(SIGN_IN);
			statement.setString(FIRST, user.getLogin());
			statement.setString(SECOND, user.getPassword());
			
			result = statement.executeQuery();
			
			if (result.first()) {
				answer = true;
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("Can't create connection", e);
		} finally {
			CloseResultSet.getInstance().close(result);
			CloseStatement.getInstance().close(statement);
		}
		return answer;
	}

	@Override
	public void registration(User user) throws DAOException{
		PreparedStatement statement = null;
		
		try (Connection connection = ConnectionPool.getInstance().takeConnection()){
			
			statement = connection.prepareStatement(REGISTRATION);
			statement.setString(FIRST, user.getLogin());
			statement.setString(SECOND, user.getPassword());
			statement.setString(THIRD, user.getEmail());
			
			statement.execute();
			
		} catch (SQLException e) {
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("Can't create connection", e);
		} finally {
			CloseStatement.getInstance().close(statement);
		}
		
	}

	@Override
	public boolean isAdmin(User user) throws DAOException {
		boolean answer = false;
		
		PreparedStatement statement = null;
		
		try(Connection connection = ConnectionPool.getInstance().takeConnection()){
			statement = connection.prepareStatement(SELECT_IS_ADMIN);
			statement.setString(FIRST, user.getLogin());
			
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				answer = true;
			}
		} catch (SQLException e) {
			throw new DAOException("sql exception while checking is user admin", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("cannot create exception due to problems with connection pool", e);
		} finally {
			CloseStatement.getInstance().close(statement);
		}
		return answer;
	}

}
