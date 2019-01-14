package by.etc.samodumkina.service.impl;

import java.util.LinkedList;
import java.util.List;

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
import by.etc.samodumkina.service.util.UserErrorMessage;
import by.etc.samodumkina.service.validator.impl.LoginValidator;
import by.etc.samodumkina.service.validator.impl.PasswordValidator;

public class SignInCommand implements Command<String>{	
	private final static String AUTORIZATION_PROBLEM = "local.autorization_problem";
	private final static String UNCORRECT_ENTRY = "local.uncorrect_login_password";
	
	private final static String TRUE = "true";
	private final static String FALSE = "false";

	@Override
	public List<String> execute(HttpServletRequest request) throws ServiceException {
		List<String> answer = new LinkedList<String>();
		
		String login = request.getParameter(RequestParameterName.LOGIN);
		String password =  request.getParameter(RequestParameterName.PASSWORD);
		
		request.getSession(true).setAttribute(SessionAttributeName.IS_REGISTRATION_FORM, FALSE);
		
		if (!LoginValidator.getInstance().isValid(login) || !PasswordValidator.getInstance().isValid(password)) {
			UserErrorMessage.show(request, UNCORRECT_ENTRY);
			answer.add(JSPPageName.CATALOG_PAGE.getURL());
		}else {
		
			User user = new User(login, password);
			
			try {
				DAOFactory factory = DAOFactory.getInstance();
				
				UserDAO userDao = factory.takeUserDAO();
				boolean result = userDao.signIn(user);
				
				if (result) {
					request.getSession(true).setAttribute(SessionAttributeName.USER_NAME, login);
					if (userDao.isAdmin(user)) {
						request.getSession(true).setAttribute(SessionAttributeName.IS_ADMIN, TRUE);
						answer.add(JSPPageName.ADMIN_MAIN_PAGE.getURL());
					}else {
						request.getSession(true).setAttribute(SessionAttributeName.IS_ADMIN, FALSE);
						answer.add(JSPPageName.CATALOG_PAGE.getURL());
					}
				} else {
					UserErrorMessage.show(request, AUTORIZATION_PROBLEM);
					answer.add(JSPPageName.MAIN_PAGE.getURL());
				}
				
			}catch (DAOException e) {
				throw new ServiceException(e);
			}
		}
		return answer;
	}
}
