package by.etc.samodumkina.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.etc.samodumkina.controller.JSPPageName;
import by.etc.samodumkina.service.Command;
import by.etc.samodumkina.service.exception.ServiceException;

public class ChangeToRULocale implements Command<String> {
	private final static String RU = "ru";
	private final static String LOCALE = "local";

	@Override
	public List<String> execute(HttpServletRequest request) throws ServiceException {
		request.getSession().setAttribute(LOCALE, RU);
		List<String> result = new LinkedList<>();
		result.add(JSPPageName.MAIN_PAGE);
		return result;
	}

}