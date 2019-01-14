package by.etc.samodumkina.service.validator.impl;

import by.etc.samodumkina.service.validator.DataValidator;

public class EmailValidator implements DataValidator<String>{

	private final static EmailValidator instance = new EmailValidator();
	
	private final static String EMPTY_STRING = "";
	private final static String SPACE = "";

	private EmailValidator() {}
	
	public static EmailValidator getInstance() {
		return instance;
	}

	@Override
	public boolean isValid(String data) {
		if (data == null || data.equals(EMPTY_STRING) || data.equals(SPACE)) {
			return false;
		}
		return true;
	}

}
