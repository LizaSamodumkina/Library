package by.etc.samodumkina.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.etc.samodumkina.bean.PreOrder;
import by.etc.samodumkina.bean.User;
import by.etc.samodumkina.controller.JSPPageName;
import by.etc.samodumkina.dao.exception.DAOException;
import by.etc.samodumkina.dao.factory.DAOFactory;
import by.etc.samodumkina.service.Command;
import by.etc.samodumkina.service.exception.ServiceException;

public class TakeBookToReadingRoomCommand implements Command<String> {
	private final static String BOOK_ID = "likedBookId";
	private final static String USER_NAME = "user";

	@Override
	public List<String> execute(HttpServletRequest request) throws ServiceException {
		String bookId = (String) request.getParameter(BOOK_ID);
		String userName = (String) request.getSession().getAttribute(USER_NAME);
		boolean isOrderToHome = false;
		
		PreOrder preorder = new PreOrder(new User(userName), bookId, isOrderToHome);
		List<PreOrder> data = new ArrayList<>();
		data.add(preorder);
		
		try {
			DAOFactory.getInstance().takeAddBookToQueue().add(data);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		List<String> page = new LinkedList<>();
		page.add(JSPPageName.ORDERS_PAGE.getURL());
		return page;
	}
}

