package by.etc.samodumkina.dao.factory;

import by.etc.samodumkina.bean.Book;
import by.etc.samodumkina.bean.NeedSendOutBook;
import by.etc.samodumkina.bean.Order;
import by.etc.samodumkina.bean.PreOrder;
import by.etc.samodumkina.bean.User;
import by.etc.samodumkina.dao.AddInfoDAO;
import by.etc.samodumkina.dao.BookDAO;
import by.etc.samodumkina.dao.DeleteInfoDAO;
import by.etc.samodumkina.dao.TakeInfoDAO;
import by.etc.samodumkina.dao.UserDAO;
import by.etc.samodumkina.dao.impl.SQLAddBookToQueue;
import by.etc.samodumkina.dao.impl.SQLAddUserLikedBook;
import by.etc.samodumkina.dao.impl.SQLBookDAO;
import by.etc.samodumkina.dao.impl.SQLCloseOrder;
import by.etc.samodumkina.dao.impl.SQLCreateOrder;
import by.etc.samodumkina.dao.impl.SQLDeleteBlockedUser;
import by.etc.samodumkina.dao.impl.SQLDeleteUserLikedBook;
import by.etc.samodumkina.dao.impl.SQLReadBlockedUsers;
import by.etc.samodumkina.dao.impl.SQLReadBook;
import by.etc.samodumkina.dao.impl.SQLReadNeedSendOutBook;
import by.etc.samodumkina.dao.impl.SQLReadOrder;
import by.etc.samodumkina.dao.impl.SQLReadOrderStory;
import by.etc.samodumkina.dao.impl.SQLReadUserLikedBook;
import by.etc.samodumkina.dao.impl.SQLReadUserToNotificate;
import by.etc.samodumkina.dao.impl.SQLUserDAO;

public class DAOFactory {
	private final static DAOFactory instance = new DAOFactory();
	
	private final UserDAO sqlUserImpl = new SQLUserDAO();
	private final TakeInfoDAO<Book> sqlReadBook = new SQLReadBook();
	private final AddInfoDAO<String> sqlAddUserLikedBook = new SQLAddUserLikedBook();
	private final TakeInfoDAO<Book> sqlReadUserLikedBook = new SQLReadUserLikedBook();
	private final AddInfoDAO<PreOrder> sqlAddBookToQueue = new SQLAddBookToQueue();
	private final TakeInfoDAO<NeedSendOutBook> sqlReadNeedSendOutBookToReadingRoom = new SQLReadNeedSendOutBook();
	private final AddInfoDAO<String> sqlCreateOrder = new SQLCreateOrder();
	private final TakeInfoDAO<Order> sqlReadOrderStory = new SQLReadOrderStory();
	private final TakeInfoDAO<Order> sqlReadOrder = new SQLReadOrder();
	private final BookDAO sqlBookDAO = new SQLBookDAO();
	private final DeleteInfoDAO sqlDeleteLikedBook = new SQLDeleteUserLikedBook();
	private final TakeInfoDAO<User> sqlReadBlockedUsers = new SQLReadBlockedUsers();
	private final DeleteInfoDAO sqlDeleteBlokedUser = new SQLDeleteBlockedUser();
	private final TakeInfoDAO<String> sqlReadUserToNotificate = new SQLReadUserToNotificate();
	private final AddInfoDAO<String> sqlCloseOrder = new SQLCloseOrder();
	
	private DAOFactory() {}
	
	public static DAOFactory getInstance() {
		return instance;
	}
	
	public UserDAO takeUserDAO() {
		return sqlUserImpl;
	}
	
	public TakeInfoDAO<Book> takeBookReader() {
		return sqlReadBook;
	}
	
	public AddInfoDAO<String> takeAddUserLikedBook(){
		return sqlAddUserLikedBook;
	}
	
	public TakeInfoDAO<Book> takeReadUserLikedBook(){
		return this.sqlReadUserLikedBook;
	}
	
	public AddInfoDAO<PreOrder> takeAddBookToQueue(){
		return this.sqlAddBookToQueue;
	}
	
	public TakeInfoDAO<NeedSendOutBook> takeReadSendOutBookToReadingRoom(){
		return this.sqlReadNeedSendOutBookToReadingRoom;
	}
	
	public AddInfoDAO<String> takeCreateOrder(){
		return this.sqlCreateOrder;
	}
	
	public TakeInfoDAO<Order> takeReadOrderStory(){
		return this.sqlReadOrderStory;
	}
	
	public TakeInfoDAO<Order> takeReadOrder(){
		return this.sqlReadOrder;
	}
	
	public BookDAO takeBookDAO() {
		return sqlBookDAO;
	}
	
	public DeleteInfoDAO takeDeleteUserLikedBook() {
		return sqlDeleteLikedBook;
	}
	
	public TakeInfoDAO<User> takeReadBlockedUsers(){
		return sqlReadBlockedUsers;
	}
	
	public DeleteInfoDAO takeDeleteBlockedUser() {
		return sqlDeleteBlokedUser;
	}
	
	public TakeInfoDAO<String> takeReadUserToNotificate(){
		return sqlReadUserToNotificate;
	}
	
	public AddInfoDAO<String> takeCloseOrder(){
		return sqlCloseOrder;
	}
}
