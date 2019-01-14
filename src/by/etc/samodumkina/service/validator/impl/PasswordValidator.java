package by.etc.samodumkina.service.validator.impl;

import by.etc.samodumkina.service.validator.DataValidator;

public class PasswordValidator implements DataValidator<String> {
	private final static PasswordValidator instance = new PasswordValidator();
	
	private final static String EMPTY_STRING = "";
	private final static String SPACE = "";

	private PasswordValidator() {}
	
	public static PasswordValidator getInstance() {
		return instance;
	}

	@Override
	public boolean isValid(String data) {
		if (data == null || data.equals(EMPTY_STRING) || data.equals(SPACE))
			return false;
		return true;
	}

}
