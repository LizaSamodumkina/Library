package by.etc.samodumkina.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import by.etc.samodumkina.bean.Book;
import by.etc.samodumkina.controller.JSPPageName;
import by.etc.samodumkina.controller.RequestParameterName;
import by.etc.samodumkina.controller.SessionAttributeName;
import by.etc.samodumkina.dao.exception.DAOException;
import by.etc.samodumkina.dao.factory.DAOFactory;
import by.etc.samodumkina.service.Command;
import by.etc.samodumkina.service.exception.ServiceException;
import by.etc.samodumkina.service.validator.impl.BookDataValidator;
import by.etc.samodumkina.service.validator.impl.ColBookInLibraryValidator;
import by.etc.samodumkina.util.PropertyResourceManager;

public class AddNewBookCommand implements Command<String>{

	private final static String ERROR_MESSAGE = "error_message";
	private final static String COPY_NUM_ERROR = "local.copy_num_error";

	@Override
	public List<String> execute(HttpServletRequest request) throws ServiceException {
		List<String> page = new LinkedList<>();
		page.add(JSPPageName.ADMIN_ADD_NEW_BOOK_PAGE.getURL());
		
		String bookName = request.getParameter(RequestParameterName.BOOK_NAME);
		String authors = request.getParameter(RequestParameterName.AUTHORS);
		String description = request.getParameter(RequestParameterName.DESCRIPTION);
		String annotation = request.getParameter(RequestParameterName.ANNOTATION);
		String copyNum = request.getParameter(RequestParameterName.COPY_NUM);
		
		if (!ColBookInLibraryValidator.getInstance().isValid(copyNum)) {
			showUserErrorMessage(request, COPY_NUM_ERROR);
			return page;
		}
		
		Book book = new Book(bookName, authors, annotation, description, Integer.parseInt(copyNum));
		
		if (!BookDataValidator.getInstance().isValid(book)) {
			//cообщение об ошибке
			return page;
		}
		
		try {
			DAOFactory.getInstance().takeBookDAO().addBook(book);
			System.out.println("ok");
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return page;
	}

	private void showUserErrorMessage(HttpServletRequest request, String message) {
		PropertyResourceManager property = new PropertyResourceManager();
		String locale = (String) request.getSession().getAttribute(SessionAttributeName.LOCALE);
		if (locale != null) {
			property.setLocale(new Locale(locale));
		}
		String mes = property.receiveMessage(message);
		request.getSession().setAttribute(ERROR_MESSAGE, mes);
		System.out.println(request.getSession().getAttribute(ERROR_MESSAGE));
	}
}
