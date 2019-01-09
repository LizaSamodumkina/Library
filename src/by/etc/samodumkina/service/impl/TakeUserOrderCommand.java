package by.etc.samodumkina.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.etc.samodumkina.bean.Order;
import by.etc.samodumkina.dao.exception.DAOException;
import by.etc.samodumkina.dao.factory.DAOFactory;
import by.etc.samodumkina.service.Command;
import by.etc.samodumkina.service.exception.ServiceException;
import by.etc.samodumkina.specification.Specification;
import by.etc.samodumkina.specification.impl.TakeUserOrderSpecification;

public class TakeUserOrderCommand implements Command<Order>{
	private final static String USER_NAME = "user";

	@Override
	public List<Order> execute(HttpServletRequest request) throws ServiceException {
		String userName = (String) request.getSession().getAttribute(USER_NAME);
		
		Specification specification = new TakeUserOrderSpecification(userName);
		
		List<Order> orders = null;
		
		try {
			orders = DAOFactory.getInstance().takeReadOrder().read(specification);
			
			for(Order a: orders) {
				System.out.println(a);
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return orders;		
	}

}
