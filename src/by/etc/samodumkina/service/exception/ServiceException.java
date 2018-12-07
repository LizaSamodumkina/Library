package by.etc.samodumkina.service.exception;

public class ServiceException extends Exception {
	private static final long serialVersionUID = -5976076569431153058L;

	public ServiceException() {}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Exception e) {
		super(e);
	}

	public ServiceException(String message, Exception e) {
		super(message, e);
	}

}
