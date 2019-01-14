package by.etc.samodumkina.service.validator.impl;

import by.etc.samodumkina.service.validator.DataValidator;

public class ColBookInLibraryValidator implements DataValidator<String>{
	private final static ColBookInLibraryValidator instance = new ColBookInLibraryValidator();
	
	private ColBookInLibraryValidator() {}
	
	public static  ColBookInLibraryValidator getInstance() {
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
