package by.etc.samodumkina.dao.factory;

import by.etc.samodumkina.dao.BookDAO;
import by.etc.samodumkina.dao.UserDAO;
import by.etc.samodumkina.dao.impl.SQLBookDAO;
import by.etc.samodumkina.dao.impl.SQLUserDAO;

public class DAOFactory {
	private final static DAOFactory instance = new DAOFactory();
	
	private final UserDAO sqlUserImpl = new SQLUserDAO();
	private final BookDAO sqlBookImpl = new SQLBookDAO();
	
	private DAOFactory() {}
	
	public static DAOFactory getInstance() {
		return instance;
	}
	
	public UserDAO takeUserDAO() {
		return sqlUserImpl;
	}
	
	public BookDAO takeBookDAO() {
		return sqlBookImpl;
	}
}
