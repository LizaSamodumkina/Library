package by.etc.samodumkina.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.etc.samodumkina.bean.Order;
import by.etc.samodumkina.dao.exception.DAOException;
import by.etc.samodumkina.dao.factory.DAOFactory;
import by.etc.samodumkina.service.Command;
import by.etc.samodumkina.service.exception.ServiceException;
import by.etc.samodumkina.specification.Specification;
import by.etc.samodumkina.specification.impl.TakeAdminOrderStoryByUserSpecification;

public class TakeUserOrderStoryCommand implements Command<Order> {
	private final static String USER_NAME = "user";

	@Override
	public List<Order> execute(HttpServletRequest request) throws ServiceException {
		String userName = (String) request.getSession().getAttribute(USER_NAME);
		
		Specification specification = new TakeAdminOrderStoryByUserSpecification(userName);
		
		List<Order> orders = null;
		
		try {
			orders = DAOFactory.getInstance().takeReadOrderStory().read(specification);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return orders;
	}

}
