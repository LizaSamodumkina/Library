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

public class SQLUserDAO implements UserDAO{
	private final static String SIGN_IN = "select * from users where login = ? and password = ?";
	private final static String REGISTRATION  = "insert into users (login, password, e_mail) values(?, ?, ?)";

	public SQLUserDAO() {}

	@Override
	public boolean signIn(User user) throws DAOException{
		
		boolean answer = false;
		try {
			Connection connection = ConnectionPool.getInstance().takeConnection();
			
			PreparedStatement statement = connection.prepareStatement(SIGN_IN);
			statement.setString(1, user.getLogin());
			statement.setString(2, user.getPassword());
			
			ResultSet result = statement.executeQuery();
			
			if (result.first()) {
				answer = true;
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("Can't create connection", e);
		}
		
		return answer;
	}

	@Override
	public void registration(User user) throws DAOException{
		try {
			Connection connection = ConnectionPool.getInstance().takeConnection();
			PreparedStatement statement = connection.prepareStatement(REGISTRATION);
			statement.setString(1, user.getLogin());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getEmail());
			
			statement.execute();
			
		} catch (SQLException e) {
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("Can't create connection", e);
		}
		
	}

}
