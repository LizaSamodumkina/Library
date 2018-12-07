package by.etc.samodumkina.service;

import javax.servlet.http.HttpServletRequest;

import by.etc.samodumkina.service.exception.ServiceException;

public interface Command {
	public String execute(HttpServletRequest request) throws ServiceException;
}
