package by.etc.samodumkina.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.etc.samodumkina.controller.JSPPageName;
import by.etc.samodumkina.service.Command;
import by.etc.samodumkina.service.exception.ServiceException;

public class GetAdminAddNewBookPageCommand implements Command<String> {
	//private final static String ERROR_MESSAGE = "error_message";
	//private final static String EMPTY_STRING = "";


	@Override
	public List<String> execute(HttpServletRequest request) throws ServiceException {
		//request.getSession().setAttribute(ERROR_MESSAGE, EMPTY_STRING);
		
		List<String> page = new LinkedList<>();
		page.add(JSPPageName.ADMIN_ADD_NEW_BOOK_PAGE.getURL());
		return page;
	}

}
