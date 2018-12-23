package by.etc.samodumkina.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.etc.samodumkina.bean.Book;
import by.etc.samodumkina.dao.exception.DAOException;
import by.etc.samodumkina.dao.factory.DAOFactory;
import by.etc.samodumkina.service.Command;
import by.etc.samodumkina.service.exception.ServiceException;

public class TakeAllBookCommand implements Command<Book> {

	@Override
	public List<Book> execute(HttpServletRequest request) throws ServiceException {
				
		List<Book> books = new LinkedList<>();
		try {
			books = DAOFactory.getInstance().takeBookDAO().read();
		} catch (DAOException e) {
			throw new ServiceException("cannot take all books due to problems with DAL", e);
		}
		return books;
	}

}
