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
import by.etc.samodumkina.service.util.UserErrorMessage;
import by.etc.samodumkina.service.validator.impl.LoginValidator;
import by.etc.samodumkina.specification.Specification;
import by.etc.samodumkina.specification.impl.DeleteBlockedUserSpecification;

public class DeleteBlockedUserCommand implements Command<String>{
	
	private static final String SUCCESS_MESSAGE = "local.delete_blocked_user";

	@Override
	public List<String> execute(HttpServletRequest request) throws ServiceException {
		List<String> page = new LinkedList<>();
		String login = request.getParameter(RequestParameterName.LOGIN);
		
		if (!LoginValidator.getInstance().isValid(login)) {
			//неправильно сформирован запрос
			page.add(JSPPageName.REDIRECT_MAIN_PAGE.getURL());
		} else {
			try {
				Specification specification = new DeleteBlockedUserSpecification(login);
				
				DAOFactory.getInstance().takeDeleteBlockedUser().delete(specification);
				
				UserErrorMessage.show(request, SUCCESS_MESSAGE);
				page.add(JSPPageName.BLOCKED_USERS_PAGE.getURL());
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
		}
		
		return page;
	}

}
