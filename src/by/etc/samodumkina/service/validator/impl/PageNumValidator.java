package by.etc.samodumkina.service.validator.impl;

import by.etc.samodumkina.service.validator.DataValidator;

public class PageNumValidator implements DataValidator<String> {

	private final static PageNumValidator instance = new PageNumValidator();
	
	private PageNumValidator() {}
	
	public static  PageNumValidator getInstance() {
		return instance;
	}

	@Override
	public boolean isValid(String data) {
		boolean answer = true;
		try {
			Integer temp = Integer.parseInt(data);
			
			if (temp < 1) {
				answer = false;
			}
		} catch(NumberFormatException e) {
			answer = false;
		}
		
		return answer;
	}

}
