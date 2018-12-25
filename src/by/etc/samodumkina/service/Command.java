package by.etc.samodumkina.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.etc.samodumkina.service.exception.ServiceException;

public interface Command<T> {
	public List<T> execute(HttpServletRequest request) throws ServiceException;
}
