package by.etc.samodumkina.dao.exception;

public class AlreadyExistDAOException extends Exception {

	private static final long serialVersionUID = 7483562938927647679L;

	public AlreadyExistDAOException() {}

	public AlreadyExistDAOException(String m) {
		super(m);
	}

	public AlreadyExistDAOException(Exception e) {
		super(e);
	}

	public AlreadyExistDAOException(String m, Exception e) {
		super(m, e);
	}

}
