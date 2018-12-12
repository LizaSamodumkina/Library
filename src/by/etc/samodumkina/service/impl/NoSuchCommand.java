package by.etc.samodumkina.service.impl;

import javax.servlet.http.HttpServletRequest;

import by.etc.samodumkina.controller.JSPPageName;
import by.etc.samodumkina.service.Command;

public class NoSuchCommand implements Command{
	
	@Override
	public String execute(HttpServletRequest request) {
		return JSPPageName.ERROR_PAGE;
	}

}
