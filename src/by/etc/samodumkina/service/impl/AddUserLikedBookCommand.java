package by.etc.samodumkina.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.etc.samodumkina.controller.JSPPageName;
import by.etc.samodumkina.controller.RequestParameterName;
import by.etc.samodumkina.dao.AddInfoDAO;
import by.etc.samodumkina.dao.exception.DAOException;
import by.etc.samodumkina.dao.factory.DAOFactory;
import by.etc.samodumkina.service.Command;
import by.etc.samodumkina.service.exception.ServiceException;

public class AddUserLikedBookCommand implements Command<String>{
	private final static String EMPTY_STRING = "";
	private final static String SPACE = " ";
	private final static String USER_NAME = "user";

	@Override
	public List<String> execute(HttpServletRequest request) throws ServiceException {
		String bookId = request.getParameter(RequestParameterName.BOOK_ID);
		if (bookId == EMPTY_STRING || bookId == SPACE) {
			 //TODO
			 System.out.println("bookId trouble");
		}
		 
		String login = (String)request.getSession().getAttribute(USER_NAME);
		 
		AddInfoDAO<String> adder = DAOFactory.getInstance().takeAddUserLikedBook();
	
		List<String> infoToAdd = new LinkedList<>();
		infoToAdd.add(login);
		infoToAdd.add(bookId);
		
		List<String> answer = new LinkedList<String>();
		try {
			adder.add(infoToAdd);
			answer.add(JSPPageName.CATALOG_PAGE);
		} catch (DAOException e) {
			throw new ServiceException("can't add user liked book", e);
		}
		
		return answer;
	}

}
