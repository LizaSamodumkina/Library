package by.etc.samodumkina.service.validator.impl;

import java.util.Calendar;

import by.etc.samodumkina.service.validator.DataValidator;

public class PublishingYearValidator implements DataValidator<String> {

	private final static PublishingYearValidator instance = new PublishingYearValidator();
	
	private PublishingYearValidator() {}
	
	public static  PublishingYearValidator getInstance() {
		return instance;
	}

	@Override
	public boolean isValid(String data) {
		boolean answer = true;
		try {
			Integer temp = Integer.parseInt(data);
			
			Calendar date = Calendar.getInstance();
			if (temp < 1 || temp > date.get(Calendar.YEAR)) {
				answer = false;
			}
		} catch(NumberFormatException e) {
			answer = false;
		}
		
		return answer;
	}

}
