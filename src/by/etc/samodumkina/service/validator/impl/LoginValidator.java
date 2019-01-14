package by.etc.samodumkina.service.validator.impl;

import by.etc.samodumkina.service.validator.DataValidator;

public class LoginValidator implements DataValidator<String> {
	private final static LoginValidator instance = new LoginValidator();
	
	private final static String EMPTY_STRING = "";
	private final static String SPACE = "";

	private LoginValidator() {}
	
	public static LoginValidator getInstance() {
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
