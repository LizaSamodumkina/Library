package by.etc.samodumkina.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.etc.samodumkina.bean.NeedSendOutBook;
import by.etc.samodumkina.controller.JSPPageName;
import by.etc.samodumkina.controller.RequestAttributeName;
import by.etc.samodumkina.dao.exception.DAOException;
import by.etc.samodumkina.dao.factory.DAOFactory;
import by.etc.samodumkina.service.Command;
import by.etc.samodumkina.service.exception.ServiceException;
import by.etc.samodumkina.specification.Specification;
import by.etc.samodumkina.specification.impl.TakeNeedSendOutBookToReadingRoomByUserSpecification;
import by.etc.samodumkina.specification.impl.TakeNeedSendOutBookToReadingRoomSpecification;

public class TakeListOfBookNeedSendOutToReadingRoomCommand implements Command<String>{
	private final static String TRUE = "true";

	@Override
	public List<String> execute(HttpServletRequest request) throws ServiceException {
		String user = request.getParameter("userSearch");
		
		List<String> result = new LinkedList<>();
		Specification specification = null;
		
		if (user != null) {
			specification = new TakeNeedSendOutBookToReadingRoomByUserSpecification(user);
		}else {
			specification = new TakeNeedSendOutBookToReadingRoomSpecification();
		}
		
		try {
			List<NeedSendOutBook> books = DAOFactory.getInstance().takeReadSendOutBookToReadingRoom().read(specification);
			
			request.setAttribute(RequestAttributeName.LIST, books);
			request.setAttribute(RequestAttributeName.IS_READING_ROOM_INFO, TRUE);
			
			result.add(JSPPageName.ADMIN_MAIN_PAGE.getURL());
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return result;
	}

}
