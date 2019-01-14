package by.etc.samodumkina.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import by.etc.samodumkina.dao.TakeInfoDAO;
import by.etc.samodumkina.dao.exception.DAOException;
import by.etc.samodumkina.dao.pool.ConnectionPool;
import by.etc.samodumkina.dao.pool.exception.ConnectionPoolException;
import by.etc.samodumkina.dao.util.CloseResultSet;
import by.etc.samodumkina.dao.util.CloseStatement;
import by.etc.samodumkina.specification.Specification;

public class SQLReadUserToNotificate implements TakeInfoDAO<String> {
	private final static String E_MAIL = "users.e_mail";

	public SQLReadUserToNotificate() {}

	@Override
	public List<String> read(Specification specification) throws DAOException {
		List<String> emails = new LinkedList<>();
		
		Statement statement = null;
		ResultSet resultSet = null;
		
		try(Connection connection = ConnectionPool.getInstance().takeConnection()){
			
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery(specification.toQuery());
			
			while (resultSet.next()) {
				String email = resultSet.getString(E_MAIL);
				emails.add(email);
			}
			
		} catch (SQLException e) {
			throw new DAOException("sql exception while reading emails from database", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("cannot create exception due to problems with connection pool", e);
		} finally {
			CloseResultSet.getInstance().close(resultSet);
			CloseStatement.getInstance().close(statement);
		}
		
		return emails;
	}

}
