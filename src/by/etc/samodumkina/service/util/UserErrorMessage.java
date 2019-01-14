package by.etc.samodumkina.service.util;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import by.etc.samodumkina.controller.SessionAttributeName;
import by.etc.samodumkina.util.PropertyResourceManager;

public class UserErrorMessage {

	public static void show(HttpServletRequest request, String message) {
		PropertyResourceManager property = new PropertyResourceManager();
		String locale = (String) request.getSession().getAttribute(SessionAttributeName.LOCALE);
		if (locale != null) {
			property.setLocale(new Locale(locale));
		}
		String mes = property.receiveMessage(message);
		request.getSession().setAttribute(SessionAttributeName.ERROR_MESSAGE, mes);
	}

}
