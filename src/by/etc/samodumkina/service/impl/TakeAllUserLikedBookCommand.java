package by.etc.samodumkina.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.etc.samodumkina.bean.Book;
import by.etc.samodumkina.dao.exception.DAOException;
import by.etc.samodumkina.dao.factory.DAOFactory;
import by.etc.samodumkina.service.Command;
import by.etc.samodumkina.service.exception.ServiceException;
import by.etc.samodumkina.specification.Specification;
import by.etc.samodumkina.specification.impl.UserLikedBookSpecification;

public class TakeAllUserLikedBookCommand implements Command<Book> {
	private final static String USER_NAME = "user";

	@Override
	public List<Book> execute(HttpServletRequest request) throws ServiceException {
		String login = (String) request.getSession().getAttribute(USER_NAME);
		
		Specification specification = new UserLikedBookSpecification(login);
		
		List<Book> books = new LinkedList<>();
		
		try {
			books = DAOFactory.getInstance().takeReadUserLikedBook().read(specification);
		} catch (DAOException e) {
			throw new ServiceException("can't read user liked books", e);
		}
		
		return books;
	}

}
