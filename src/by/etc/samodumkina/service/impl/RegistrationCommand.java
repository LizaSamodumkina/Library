package by.etc.samodumkina.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.etc.samodumkina.bean.User;
import by.etc.samodumkina.controller.JSPPageName;
import by.etc.samodumkina.controller.RequestParameterName;
import by.etc.samodumkina.controller.SessionAttributeName;
import by.etc.samodumkina.dao.UserDAO;
import by.etc.samodumkina.dao.exception.AlreadyExistDAOException;
import by.etc.samodumkina.dao.exception.DAOException;
import by.etc.samodumkina.dao.factory.DAOFactory;
import by.etc.samodumkina.service.Command;
import by.etc.samodumkina.service.exception.ServiceException;
import by.etc.samodumkina.service.util.UserErrorMessage;
import by.etc.samodumkina.service.validator.impl.EmailValidator;
import by.etc.samodumkina.service.validator.impl.LoginValidator;
import by.etc.samodumkina.service.validator.impl.PasswordValidator;

public class RegistrationCommand implements Command<String>{
	
	private final static String UNCORRECT_ENTRY = "local.uncorrect_login_password";
	private final static String REGASTRATION_PROBLEM = "local.cant_registrate";
	
	private final static String TRUE = "true";
	private final static String FALSE = "false";

	@Override
	public List<String> execute(HttpServletRequest request) throws ServiceException {
		List<String> answer = new LinkedList<>();
		
		String login = request.getParameter(RequestParameterName.LOGIN);
		String password =  request.getParameter(RequestParameterName.PASSWORD);
		String email = request.getParameter(RequestParameterName.EMAIL);
		
		if (!LoginValidator.getInstance().isValid(login) || !PasswordValidator.getInstance().isValid(password) || 
				!EmailValidator.getInstance().isValid(email)) {
			UserErrorMessage.show(request, UNCORRECT_ENTRY);
			request.getSession(true).setAttribute(SessionAttributeName.IS_REGISTRATION_FORM, TRUE);
			answer.add(JSPPageName.MAIN_PAGE.getURL());
		} else {
			User user = new User(login, password, email);
			
			try {
				DAOFactory factory = DAOFactory.getInstance();
				
				UserDAO userDao = factory.takeUserDAO();
				
				userDao.registration(user);
				
				request.getSession(true).setAttribute(SessionAttributeName.USER_NAME, login);
				request.getSession(true).setAttribute(SessionAttributeName.IS_ADMIN, FALSE);
				
				answer.add(JSPPageName.CATALOG_PAGE.getURL());
			} catch (AlreadyExistDAOException e){
				UserErrorMessage.show(request, REGASTRATION_PROBLEM);
				request.getSession(true).setAttribute(SessionAttributeName.IS_REGISTRATION_FORM, TRUE);
				answer.add(JSPPageName.MAIN_PAGE.getURL());
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
		}
		
		return answer;
	}

}
