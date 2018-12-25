package by.etc.samodumkina.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.etc.samodumkina.controller.JSPPageName;
import by.etc.samodumkina.service.Command;

public class NoSuchCommand implements Command<String>{
	
	@Override
	public List<String> execute(HttpServletRequest request) {
		List<String> result = new LinkedList<>();
		result.add(JSPPageName.ERROR_PAGE.getURL());
		return result;
	}

}
