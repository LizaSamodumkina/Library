package by.etc.samodumkina.dao.factory;

import by.etc.samodumkina.dao.UserDAO;
import by.etc.samodumkina.dao.impl.SQLUserDAO;

public class DAOFactory {
	private final static DAOFactory instance = new DAOFactory();
	
	private final UserDAO sqlUserImpl = new SQLUserDAO();
	
	private DAOFactory() {}
	
	public static DAOFactory getInstance() {
		return instance;
	}
	
	public UserDAO getUserDAO() {
		return sqlUserImpl;
	}
}
