package by.etc.samodumkina.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.etc.samodumkina.controller.JSPPageName;
import by.etc.samodumkina.service.Command;
import by.etc.samodumkina.service.exception.ServiceException;

public class GetAdminDeleteBookPageCommand implements Command<String>{

	@Override
	public List<String> execute(HttpServletRequest request) throws ServiceException {
		List<String> page = new LinkedList<>();
		page.add(JSPPageName.ADMIN_DELETE_BOOK_PAGE.getURL());
		return page;
	}

}
