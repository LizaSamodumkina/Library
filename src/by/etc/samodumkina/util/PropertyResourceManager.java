package by.etc.samodumkina.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class PropertyResourceManager{
	private final static String RESOURCE_NAME = "locale";
	private Locale locale;

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public String receiveMessage(String key) {
		ResourceBundle resourceBundle;
		if (locale != null) {
			resourceBundle = ResourceBundle.getBundle(RESOURCE_NAME, locale);
		}else {
			resourceBundle = ResourceBundle.getBundle(RESOURCE_NAME);
		}
		return resourceBundle.getString(key);
	}
}
