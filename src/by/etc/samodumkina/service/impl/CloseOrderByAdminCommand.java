package by.etc.samodumkina.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.etc.samodumkina.controller.JSPPageName;
import by.etc.samodumkina.controller.RequestParameterName;
import by.etc.samodumkina.dao.exception.DAOException;
import by.etc.samodumkina.dao.factory.DAOFactory;
import by.etc.samodumkina.service.Command;
import by.etc.samodumkina.service.exception.ServiceException;
import by.etc.samodumkina.service.util.UserInfoMessage;
import by.etc.samodumkina.service.validator.impl.IdValidator;

public class CloseOrderByAdminCommand implements Command<String>{
	private final static String SUCCESS_MESSAGE = "local.close_order_message";

	@Override
	public List<String> execute(HttpServletRequest request) throws ServiceException {
		
		List<String> page = new LinkedList<>();

		String orderId = request.getParameter(RequestParameterName.ORDER_ID);
		if (!IdValidator.getInstance().isValid(orderId)) {
			page.add(JSPPageName.REDIRECT_MAIN_PAGE.getURL());
			return page;
		}

		try {
			List<String> data = new LinkedList<>();
			data.add(orderId);
			
			DAOFactory.getInstance().takeCloseOrder().add(data);
			
			page.add(JSPPageName.ADMIN_ORDER_STORY_PAGE.getURL());
			
			UserInfoMessage.show(request, SUCCESS_MESSAGE);
		} catch (DAOException e) {
			throw new ServiceException("cannot close order", e);
		}
		
		return page;
	}

}
