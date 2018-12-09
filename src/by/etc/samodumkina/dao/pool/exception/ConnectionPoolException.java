package by.etc.samodumkina.dao.pool.exception;

public class ConnectionPoolException extends Exception{

	private static final long serialVersionUID = -387192891497604951L;

	public ConnectionPoolException() {
		super();
	}

	public ConnectionPoolException(String message, Exception e) {
		super(message, e);
	}

	public ConnectionPoolException(String message) {
		super(message);
	}

	public ConnectionPoolException(Exception e) {
		super(e);
	}

	
}
