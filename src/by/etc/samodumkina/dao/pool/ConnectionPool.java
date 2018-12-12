package by.etc.samodumkina.dao.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

import by.etc.samodumkina.dao.pool.exception.ConnectionPoolException;

public class ConnectionPool {
	private static ConnectionPool instance;
	
	LinkedBlockingQueue<Connection> connections;
	
	private String driverName;
	private String url;
	private String user;
	private String password;
	private int poolSize;
	
	public AtomicInteger counter = new AtomicInteger(0); //count, how many connections is busy right now
	
	private final static int optimalPoolSize = 10;
	
	private static final ReentrantLock lock = new ReentrantLock();	

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
		try {
			lock.lock();
			if (instance == null) {
				instance = new ConnectionPool();
				instance.initialization();
			}
		} catch(ConnectionPoolException e) {
			//???????????????
		} finally {
			lock.unlock();
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
		Connection con =  connections.poll();
		try {
		if(con == null && counter.get() < poolSize) {
			con = new PooledConnection(DriverManager.getConnection(url, user, password));
		}
		if(con == null)
				con = connections.take();
		counter.incrementAndGet();
		} catch(SQLException e) {
			throw new ConnectionPoolException("SQL exception in Connection Pool", e);
		} catch (InterruptedException e) {
			throw new ConnectionPoolException("Error connecting to a database", e);
		}
		return con;
	}
	
	public void closeConnection(Connection con) throws ConnectionPoolException {
		try {
			con.close();
			counter.decrementAndGet();
		} catch(SQLException e) {
			throw new ConnectionPoolException("Can't close connection", e);
		}
	}
	
	public void closePool() throws SQLException {
		Connection con;
		while((con = connections.poll()) != null) {
			((PooledConnection) con).release();
		}
		Enumeration<java.sql.Driver> drivers = DriverManager.getDrivers();		
		while(drivers.hasMoreElements()) {
			DriverManager.deregisterDriver(drivers.nextElement());
		}
		instance = null;
	}
}
