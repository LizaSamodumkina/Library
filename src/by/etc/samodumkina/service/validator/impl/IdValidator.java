package by.etc.samodumkina.service.validator.impl;

import by.etc.samodumkina.service.validator.DataValidator;

public class IdValidator implements DataValidator<String> {
	private final static String EMPTY_STRING = "";
	private final static String SPACE = " ";
	private final static IdValidator instance = new IdValidator();

	private IdValidator() {}
	
	public static IdValidator getInstance() {
		return instance;
	}

	@Override
	public boolean isValid(String data) {
		if (data == null || data.equals(EMPTY_STRING) || data.equals(SPACE)) {
			return false;
		}else {
			return true;
		}
	}

}
