package by.etc.samodumkina.dao;

import by.etc.samodumkina.bean.Book;
import by.etc.samodumkina.dao.exception.DAOException;

public interface BookDAO {
	public void addBook(Book book) throws DAOException;
	public void deleteBook(String bookId) throws DAOException;
}
