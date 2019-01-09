package by.etc.samodumkina.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import by.etc.samodumkina.bean.User;
import by.etc.samodumkina.controller.JSPPageName;
import by.etc.samodumkina.controller.RequestParameterName;
import by.etc.samodumkina.controller.SessionAttributeName;
import by.etc.samodumkina.dao.UserDAO;
import by.etc.samodumkina.dao.exception.DAOException;
import by.etc.samodumkina.dao.factory.DAOFactory;
import by.etc.samodumkina.service.Command;
import by.etc.samodumkina.service.exception.ServiceException;
import by.etc.samodumkina.util.PropertyResourceManager;

public class SignInCommand implements Command<String>{
	private final static String USER_NAME = "user";
	private final static String EMPTY_STRING = "";
	private final static String LOCALE = "local";
	private final static String AUTORIZATION_PROBLEM = "local.autorization_problem";
	private final static String ERROR_MESSAGE = "error_message";
	private final static String IS_ADMIN = "is_admin";
	private final static String TRUE = "true";
	private final static String FALSE = "false";

	@Override
	public List<String> execute(HttpServletRequest request) throws ServiceException {
		List<String> answer = new LinkedList<String>();
		
		String login = request.getParameter(RequestParameterName.LOGIN);
		String password =  request.getParameter(RequestParameterName.PASSWORD);
		
		if (login.equals(EMPTY_STRING) || password.equals(EMPTY_STRING)) {
			//тут должен быть валидатор
		}
		
		User user = new User(login, password);
		
		try {
			DAOFactory factory = DAOFactory.getInstance();
			
			UserDAO userDao = factory.takeUserDAO();
			boolean result = userDao.signIn(user);
			
			if (result) {
				request.getSession(true).setAttribute(USER_NAME, login);
				if (userDao.isAdmin(user)) {
					request.getSession(true).setAttribute(IS_ADMIN, TRUE);
					answer.add(JSPPageName.ADMIN_MAIN_PAGE.getURL());
				}else {
					request.getSession(true).setAttribute(IS_ADMIN, FALSE);
					answer.add(JSPPageName.CATALOG_PAGE.getURL());
				}
			} else {
				PropertyResourceManager property = new PropertyResourceManager();
				String locale = (String) request.getSession().getAttribute(SessionAttributeName.LOCALE);
				if (locale != null) {
					property.setLocale(new Locale(locale));
				}
				String message = property.receiveMessage(AUTORIZATION_PROBLEM);
				request.setAttribute(ERROR_MESSAGE, message);
				answer.add(JSPPageName.MAIN_PAGE.getURL());
			}
			
		}catch (DAOException e) {
			throw new ServiceException(e);
		}
		return answer;
	}

}
