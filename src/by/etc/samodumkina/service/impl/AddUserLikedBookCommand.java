package by.etc.samodumkina.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.etc.samodumkina.controller.JSPPageName;
import by.etc.samodumkina.controller.RequestParameterName;
import by.etc.samodumkina.controller.SessionAttributeName;
import by.etc.samodumkina.dao.AddInfoDAO;
import by.etc.samodumkina.dao.exception.DAOException;
import by.etc.samodumkina.dao.factory.DAOFactory;
import by.etc.samodumkina.service.Command;
import by.etc.samodumkina.service.exception.ServiceException;
import by.etc.samodumkina.service.util.UserErrorMessage;
import by.etc.samodumkina.service.validator.impl.IdValidator;

public class AddUserLikedBookCommand implements Command<String>{
	private final static String SUCCESS_MESSAGE = "local.success_add_book_to_liked";

	@Override
	public List<String> execute(HttpServletRequest request) throws ServiceException {
		List<String> answer = new LinkedList<String>();
		
		String bookId = request.getParameter(RequestParameterName.BOOK_ID);
		if (! IdValidator.getInstance().isValid(bookId)) {
			//произошла ошибка в индексах. Лучше перезапустить приложение
			answer.add(JSPPageName.REDIRECT_MAIN_PAGE.getURL());
		}else {
			String login = (String)request.getSession().getAttribute(SessionAttributeName.USER_NAME);
			 
			AddInfoDAO<String> adder = DAOFactory.getInstance().takeAddUserLikedBook();
		
			List<String> infoToAdd = new LinkedList<>();
			infoToAdd.add(login);
			infoToAdd.add(bookId);
			
			try {
				adder.add(infoToAdd);
				UserErrorMessage.show(request, SUCCESS_MESSAGE);
				answer.add(JSPPageName.CATALOG_PAGE.getURL());
			} catch (DAOException e) {
				throw new ServiceException("can't add user liked book", e);
			}
		}
		
		return answer;
	}

}
