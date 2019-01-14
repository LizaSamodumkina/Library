package by.etc.samodumkina.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.etc.samodumkina.bean.Book;
import by.etc.samodumkina.controller.JSPPageName;
import by.etc.samodumkina.controller.RequestParameterName;
import by.etc.samodumkina.dao.exception.DAOException;
import by.etc.samodumkina.dao.factory.DAOFactory;
import by.etc.samodumkina.service.Command;
import by.etc.samodumkina.service.exception.ServiceException;
import by.etc.samodumkina.service.util.UserErrorMessage;
import by.etc.samodumkina.service.validator.impl.BookDataValidator;
import by.etc.samodumkina.service.validator.impl.ColBookInLibraryValidator;
import by.etc.samodumkina.service.validator.impl.PageNumValidator;
import by.etc.samodumkina.service.validator.impl.PublishingYearValidator;

public class AddNewBookCommand implements Command<String>{
	private final static String COPY_NUM_ERROR = "local.copy_num_error";
	private final static String PAGE_NUM_ERROR = "local.page_num_error";
	private final static String BOOK_ERROR = "local.book_error";
	private final static String PUBLISHING_YEAR = "local.publishing_year_error";
	private final static String SUCCESS_MESSAGE = "local.success_add_book";

	@Override
	public List<String> execute(HttpServletRequest request) throws ServiceException {
		List<String> page = new LinkedList<>();
		page.add(JSPPageName.ADMIN_ADD_NEW_BOOK_PAGE.getURL());
		
		String bookName = request.getParameter(RequestParameterName.BOOK_NAME);
		String authors = request.getParameter(RequestParameterName.AUTHORS);
		String publisher = request.getParameter(RequestParameterName.PUBLISHER);
		String publishingYear = request.getParameter(RequestParameterName.PUBLISHING_YEAR);
		String pageNum = request.getParameter(RequestParameterName.PAGE_NUM);
		String annotation = request.getParameter(RequestParameterName.ANNOTATION);
		String copyNum = request.getParameter(RequestParameterName.COPY_NUM);
		
		if (!PageNumValidator.getInstance().isValid(pageNum)) {
			UserErrorMessage.show(request, PAGE_NUM_ERROR);
			return page;
		}
		
		if (!PublishingYearValidator.getInstance().isValid(publishingYear)) {
			UserErrorMessage.show(request, PUBLISHING_YEAR);
			return page;
		}
		
		if (!ColBookInLibraryValidator.getInstance().isValid(copyNum)) {
			UserErrorMessage.show(request, COPY_NUM_ERROR);
			return page;
		}
		
		Book book = new Book(bookName, authors, annotation, Integer.parseInt(copyNum));
		book.setDescription(book.createDescription(publisher, publishingYear, pageNum));
		
		if (!BookDataValidator.getInstance().isValid(book)) {
			UserErrorMessage.show(request, BOOK_ERROR);
			return page;
		}
		
		try {
			DAOFactory.getInstance().takeBookDAO().addBook(book);
			UserErrorMessage.show(request, SUCCESS_MESSAGE);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return page;
	}
}
