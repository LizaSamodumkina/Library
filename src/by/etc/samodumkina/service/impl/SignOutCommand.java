package by.etc.samodumkina.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.etc.samodumkina.controller.JSPPageName;
import by.etc.samodumkina.service.Command;
import by.etc.samodumkina.service.exception.ServiceException;

public class SignOutCommand implements Command<String>{

	@Override
	public List<String> execute(HttpServletRequest request) throws ServiceException {
		request.getSession().invalidate();
		List<String> indexPage = new LinkedList<>();
		indexPage.add(JSPPageName.MAIN_PAGE.getURL());
		return indexPage;
	}

}
