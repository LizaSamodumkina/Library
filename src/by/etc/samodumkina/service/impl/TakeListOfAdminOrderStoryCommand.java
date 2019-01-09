package by.etc.samodumkina.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.etc.samodumkina.bean.Order;
import by.etc.samodumkina.controller.JSPPageName;
import by.etc.samodumkina.controller.RequestParameterName;
import by.etc.samodumkina.dao.exception.DAOException;
import by.etc.samodumkina.dao.factory.DAOFactory;
import by.etc.samodumkina.service.Command;
import by.etc.samodumkina.service.exception.ServiceException;
import by.etc.samodumkina.specification.Specification;
import by.etc.samodumkina.specification.impl.TakeAdminOrderStoryByUserSpecification;
import by.etc.samodumkina.specification.impl.TakeAdminOrderStorySpecification;

public class TakeListOfAdminOrderStoryCommand implements Command<String>{
	private final static String LIST = "list";

	@Override
	public List<String> execute(HttpServletRequest request) throws ServiceException {
		List<String> page = new LinkedList<>();
		
		String userName = request.getParameter(RequestParameterName.USER_SEARCH);
		Specification specification = null;
		
		if (userName != null) {
			specification = new TakeAdminOrderStoryByUserSpecification(userName);
		} else {
			specification = new TakeAdminOrderStorySpecification();
		}
		
		try {
			List<Order> orders = DAOFactory.getInstance().takeReadOrderStory().read(specification);
			
			request.setAttribute(LIST, orders);
			
			page.add(JSPPageName.ADMIN_ORDER_STORY_PAGE.getURL());
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
		
		return page;
	}

}
