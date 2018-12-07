package by.etc.samodumkina.dao.exception;

public class DAOException extends Exception {
	private static final long serialVersionUID = -3569374279505423551L;

	public DAOException() {	}

	public DAOException(String message) {
		super(message);
	}

	public DAOException(Exception e) {
		super(e);
	}

	public DAOException(String message, Exception e) {
		super(message, e);
	}

}
