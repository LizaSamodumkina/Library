package by.etc.samodumkina.dao;

import java.util.List;

import by.etc.samodumkina.bean.Book;
import by.etc.samodumkina.dao.exception.DAOException;

public interface TakeInfoDAO {
	public List<Book> read() throws DAOException;
}
