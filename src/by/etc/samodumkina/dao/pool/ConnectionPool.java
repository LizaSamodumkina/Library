package by.etc.samodumkina.dao.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingQueue;

import by.etc.samodumkina.dao.pool.exception.ConnectionPoolException;

public class ConnectionPool {
	private static ConnectionPool instance;
	
	LinkedBlockingQueue<Connection> connections;
	
	private String driverName;
	private String url;
	private String user;
	private String password;
	private int poolSize;
	
	private static int counter; //count, how many connections is busy right now
	
	private final static int optimalPoolSize = 10;

	public ConnectionPool() {
		DBResourceManager manager = DBResourceManager.getInstance();
		this.driverName = manager.getValue(DBParameter.DB_DRIVER.getValue());
		this.url = manager.getValue(DBParameter.DB_URL.getValue());
		this.user = manager.getValue(DBParameter.DB_USER.getValue());
		this.password = manager.getValue(DBParameter.DB_PASSWORD.getValue());
		try {
			this.poolSize = Integer.parseInt(manager.getValue(DBParameter.DB_POOLSIZE.getValue()));
		}catch(NumberFormatException e) {
			this.poolSize = optimalPoolSize;
		}
	}
	
	public static ConnectionPool getInstance() {
		if (instance == null) {
			instance = new ConnectionPool();
			try {
				instance.initialization();
			} catch (ConnectionPoolException e) {
				// TODO можно ли тут выкидывать исключения?
			}
		}
		return instance;
	}

	private void initialization() throws ConnectionPoolException {
		connections = new LinkedBlockingQueue<>(poolSize);
		try {
			Class.forName(this.driverName);
			for(int i = 0; i < poolSize; i ++) {
				PooledConnection connect = new PooledConnection(DriverManager.getConnection(url, user, password));
				connections.add(connect);

			}
		} catch (SQLException e) {
			throw new ConnectionPoolException("SQL exception in Connection Pool", e);
		} catch (ClassNotFoundException e) {
			throw new ConnectionPoolException("Can't find database driver class", e);
		}
	}
	
	public Connection takeConnection() throws ConnectionPoolException {
		Connection con = null;
		try {
			con = connections.take();
		} catch(InterruptedException e) {
			if(connections.isEmpty() && counter != 10) {
				//add new connection
				try {
					con = new PooledConnection(DriverManager.getConnection(url, user, password));
				} catch (SQLException e1) {
					throw new ConnectionPoolException("SQL exception in Connectoin Pool", e1);
				}
			}
			else {
				throw new ConnectionPoolException("Can't connect to the database", e);
			}
		}
		counter ++;
		return con;
	}
	
	public void closeConnection(Connection con) throws ConnectionPoolException {
		try {
			con.close();
			counter --;
		} catch(SQLException e) {
			throw new ConnectionPoolException("Can't close connection", e);
		}
	}
	
	public void closePool() throws SQLException {
		Connection con;
		while((con = connections.poll()) != null) {
			((PooledConnection) con).release();
		}
		instance = null;
	}
}
