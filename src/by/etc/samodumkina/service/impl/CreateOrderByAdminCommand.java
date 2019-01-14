package by.etc.samodumkina.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.etc.samodumkina.bean.NeedSendOutBook;
import by.etc.samodumkina.controller.JSPPageName;
import by.etc.samodumkina.controller.RequestAttributeName;
import by.etc.samodumkina.controller.RequestParameterName;
import by.etc.samodumkina.dao.exception.DAOException;
import by.etc.samodumkina.dao.factory.DAOFactory;
import by.etc.samodumkina.service.Command;
import by.etc.samodumkina.service.exception.ServiceException;
import by.etc.samodumkina.service.util.UserErrorMessage;
import by.etc.samodumkina.service.validator.impl.IdValidator;
import by.etc.samodumkina.specification.Specification;
import by.etc.samodumkina.specification.impl.TakeNeedSendOutBookToHomeSpecification;

public class CreateOrderByAdminCommand implements Command<String>{
	private final static String TRUE = "true";
	private final static String FALSE = "false";
	private final static String ERROR_MESSAGE = "local.cant_order_admin";

	@Override
	public List<String> execute(HttpServletRequest request) throws ServiceException {
		List<String> result = new LinkedList<>();
		
		String orderId = request.getParameter(RequestParameterName.ID);
		String home = request.getParameter(RequestParameterName.HOME);
		
		if (IdValidator.getInstance().isValid(orderId)) {
		
			List<String> data = new LinkedList<>();
			
			data.add(orderId);
			data.add(home);
			
			try {
				DAOFactory.getInstance().takeCreateOrder().add(data);
				
				Specification specification = new TakeNeedSendOutBookToHomeSpecification();
				List<NeedSendOutBook> books = DAOFactory.getInstance().takeReadSendOutBookToReadingRoom().read(specification);
				
				if (home.equals(TRUE)) {
					request.setAttribute(RequestAttributeName.IS_READING_ROOM_INFO, FALSE);
				} else {
					request.setAttribute(RequestAttributeName.IS_READING_ROOM_INFO, TRUE);
				}
				request.setAttribute(RequestAttributeName.LIST, books);
				
				result.add(JSPPageName.ADMIN_MAIN_PAGE.getURL());
				
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
			
		} else {
			UserErrorMessage.show(request, ERROR_MESSAGE);
			result.add(JSPPageName.ADMIN_MAIN_PAGE.getURL());
		}
		
		return result;
	}

}
