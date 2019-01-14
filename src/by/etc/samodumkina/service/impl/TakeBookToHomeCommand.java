package by.etc.samodumkina.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.etc.samodumkina.bean.PreOrder;
import by.etc.samodumkina.bean.User;
import by.etc.samodumkina.controller.JSPPageName;
import by.etc.samodumkina.controller.RequestParameterName;
import by.etc.samodumkina.controller.SessionAttributeName;
import by.etc.samodumkina.dao.exception.DAOException;
import by.etc.samodumkina.dao.factory.DAOFactory;
import by.etc.samodumkina.service.Command;
import by.etc.samodumkina.service.exception.ServiceException;
import by.etc.samodumkina.service.util.UserErrorMessage;

public class TakeBookToHomeCommand implements Command<String>{
	private final static String CANT_ORDER = "local.cant_order";
	
	@Override
	public List<String> execute(HttpServletRequest request) throws ServiceException {

		String bookId = (String) request.getParameter(RequestParameterName.LIKED_BOOK_ID);
		String userName = (String) request.getSession().getAttribute(SessionAttributeName.USER_NAME);
		boolean isOrderToHome = true;
		
		PreOrder preorder = new PreOrder(new User(userName), bookId, isOrderToHome);
		List<PreOrder> data = new ArrayList<>();
		data.add(preorder);
		
		try {
			boolean answer = DAOFactory.getInstance().takeAddBookToQueue().add(data);
			if (!answer) {
				UserErrorMessage.show(request, CANT_ORDER);
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		List<String> page = new LinkedList<>();
		page.add(JSPPageName.ORDERS_PAGE.getURL());
		return page;
	}

}
