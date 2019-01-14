package by.etc.samodumkina.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import by.etc.samodumkina.dao.DeleteInfoDAO;
import by.etc.samodumkina.dao.exception.DAOException;
import by.etc.samodumkina.dao.pool.ConnectionPool;
import by.etc.samodumkina.dao.pool.exception.ConnectionPoolException;
import by.etc.samodumkina.dao.util.CloseStatement;
import by.etc.samodumkina.specification.Specification;

public class SQLDeleteUserLikedBook implements DeleteInfoDAO {

	@Override
	public void delete(Specification specification) throws DAOException{
		System.out.println("delete");
		Statement statement = null;
		
		try(Connection connection = ConnectionPool.getInstance().takeConnection()) {
			
			statement = connection.createStatement();
			
			statement.execute(specification.toQuery());
			
		} catch (SQLException e) {
			throw new DAOException("sql exception while deleting book from database", e);
		} catch (ConnectionPoolException e) {
			throw new DAOException("cannot create exception due to problems with connection pool", e);
		} finally {
			CloseStatement.getInstance().close(statement);
		}

	}

}
