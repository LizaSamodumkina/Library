package by.etc.samodumkina.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.etc.samodumkina.controller.JSPPageName;
import by.etc.samodumkina.controller.RequestParameterName;
import by.etc.samodumkina.service.Command;
import by.etc.samodumkina.service.exception.ServiceException;

public class ChangeToENLocale implements Command<String>{
	private final static String EN = "en";
	private final static String LOCALE = "local";

	public ChangeToENLocale() {
	}

	@Override
	public List<String> execute(HttpServletRequest request) throws ServiceException {
		request.getSession().setAttribute(LOCALE, EN);
		List<String> result = new LinkedList<>();
		
		result.add(JSPPageName.valueOf(request.getParameter(RequestParameterName.CURRENT_PAGE)).getURL());
		return result;
	}

}
