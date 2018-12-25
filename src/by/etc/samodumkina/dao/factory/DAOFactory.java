package by.etc.samodumkina.dao.factory;

import by.etc.samodumkina.bean.Book;
import by.etc.samodumkina.dao.AddInfoDAO;
import by.etc.samodumkina.dao.TakeInfoDAO;
import by.etc.samodumkina.dao.UserDAO;
import by.etc.samodumkina.dao.impl.SQLAddUserLikedBook;
import by.etc.samodumkina.dao.impl.SQLReadBook;
import by.etc.samodumkina.dao.impl.SQLReadUserLikedBook;
import by.etc.samodumkina.dao.impl.SQLUserDAO;

public class DAOFactory {
	private final static DAOFactory instance = new DAOFactory();
	
	private final UserDAO sqlUserImpl = new SQLUserDAO();
	private final TakeInfoDAO<Book> sqlReadBook = new SQLReadBook();
	private final AddInfoDAO<String> sqlAddUserLikedBook = new SQLAddUserLikedBook();
	private final TakeInfoDAO<Book> sqlReadUserLikedBook = new SQLReadUserLikedBook();
	
	private DAOFactory() {}
	
	public static DAOFactory getInstance() {
		return instance;
	}
	
	public UserDAO takeUserDAO() {
		return sqlUserImpl;
	}
	
	public TakeInfoDAO<Book> takeBookDAO() {
		return sqlReadBook;
	}
	
	public AddInfoDAO<String> takeAddUserLikedBook(){
		return sqlAddUserLikedBook;
	}
	
	public TakeInfoDAO<Book> takeReadUserLikedBook(){
		return this.sqlReadUserLikedBook;
	}
}
