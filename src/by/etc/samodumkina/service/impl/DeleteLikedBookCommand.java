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
import by.etc.samodumkina.service.validator.impl.IdValidator;
import by.etc.samodumkina.specification.Specification;
import by.etc.samodumkina.specification.impl.DeleteUserLikedBookSpecification;

public class DeleteLikedBookCommand implements Command<String> {

	@Override
	public List<String> execute(HttpServletRequest request) throws ServiceException {
		List<String> answer = new LinkedList<>();
		
		String likedBookId = request.getParameter(RequestParameterName.LIKED_BOOK_ID);
		
		if (!IdValidator.getInstance().isValid(likedBookId)) {
			
		}else {
			Specification specification = new DeleteUserLikedBookSpecification(likedBookId);
			
			try {
				DAOFactory.getInstance().takeDeleteUserLikedBook().delete(specification);
				answer.add(JSPPageName.LIKED_BOOKS_PAGE.getURL());
			} catch (DAOException e) {
				throw new ServiceException("can't delete book from liked books", e);
			}
		}
		
		return answer;
	}

}
