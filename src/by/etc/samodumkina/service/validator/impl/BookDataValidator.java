package by.etc.samodumkina.service.validator.impl;

import by.etc.samodumkina.bean.Book;
import by.etc.samodumkina.service.validator.DataValidator;

public class BookDataValidator implements DataValidator<Book> {
	private final static BookDataValidator instance = new BookDataValidator();
	
	private final static String EMPTY_STRING = "";
	private final static String SPACE = " ";
	
	private BookDataValidator() {}
	
	public static BookDataValidator getInstance() {
		return instance;
	}
	
	@Override
	public boolean isValid(Book data) {
		if (data.getName() == null || data.getName().equals(EMPTY_STRING) ||data.getName().equals(SPACE) ||
				data.getAuthors() == null || data.getAuthors().equals(EMPTY_STRING) || data.getAuthors().equals(SPACE) ||
				data.getDescription() == null || data.getDescription().equals(EMPTY_STRING) || data.getDescription().equals(SPACE) ||
				data.getAnnotation() == null || data.getAnnotation().equals(EMPTY_STRING) || data.getAnnotation().equals(SPACE) ||
				data.getColInLabrary() < 1) {
			return false;
		}
		return true;
	}

}
