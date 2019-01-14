package by.etc.samodumkina.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.etc.samodumkina.bean.Order;
import by.etc.samodumkina.controller.SessionAttributeName;
import by.etc.samodumkina.dao.exception.DAOException;
import by.etc.samodumkina.dao.factory.DAOFactory;
import by.etc.samodumkina.service.Command;
import by.etc.samodumkina.service.exception.ServiceException;
import by.etc.samodumkina.specification.Specification;
import by.etc.samodumkina.specification.impl.TakeUserOrderSpecification;

public class TakeUserOrderCommand implements Command<Order>{

	@Override
	public List<Order> execute(HttpServletRequest request) throws ServiceException {
		String userName = (String) request.getSession().getAttribute(SessionAttributeName.USER_NAME);
		
		Specification specification = new TakeUserOrderSpecification(userName);
		
		List<Order> orders = null;
		
		try {
			orders = DAOFactory.getInstance().takeReadOrder().read(specification);

		} catch (DAOException e) {
			throw new ServiceException(e);
		}
				
		return orders;		
	}

}
