package by.etc.samodumkina.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.etc.samodumkina.bean.User;
import by.etc.samodumkina.controller.JSPPageName;
import by.etc.samodumkina.controller.RequestParameterName;
import by.etc.samodumkina.dao.UserDAO;
import by.etc.samodumkina.dao.exception.DAOException;
import by.etc.samodumkina.dao.factory.DAOFactory;
import by.etc.samodumkina.service.Command;
import by.etc.samodumkina.service.exception.ServiceException;

public class SignInCommand implements Command<String>{
	private final static String USER_NAME = "user";

	@Override
	public List<String> execute(HttpServletRequest request) throws ServiceException {
		List<String> answer = new LinkedList<String>();
		
		String login = request.getParameter(RequestParameterName.LOGIN);
		String password =  request.getParameter(RequestParameterName.PASSWORD);
		
		//валидаци данных
		
		User user = new User(login, password);
		
		try {
			DAOFactory factory = DAOFactory.getInstance();
			
			UserDAO userDao = factory.takeUserDAO();
			boolean result = userDao.signIn(user);
			
			if (result) {
				answer.add(JSPPageName.CATALOG_PAGE);
				request.getSession().setAttribute(USER_NAME, login);
				//проверить администратор ли в системе
			} else {
				answer.add(JSPPageName.REGISTRATION_PAGE);
			}
			
		}catch (DAOException e) {
			throw new ServiceException(e);
		}
		return answer;
	}

}
