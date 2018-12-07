package by.etc.samodumkina.service.impl;

import javax.servlet.http.HttpServletRequest;

import by.etc.samodumkina.bean.User;
import by.etc.samodumkina.controller.JSPPageName;
import by.etc.samodumkina.controller.RequestParameterName;
import by.etc.samodumkina.dao.UserDAO;
import by.etc.samodumkina.dao.exception.DAOException;
import by.etc.samodumkina.dao.factory.DAOFactory;
import by.etc.samodumkina.service.Command;
import by.etc.samodumkina.service.exception.ServiceException;

public class RegistrationCommand implements Command{

	public RegistrationCommand() {}

	@Override
	public String execute(HttpServletRequest request) throws ServiceException {
		String answer;
		
		String login = request.getParameter(RequestParameterName.LOGIN);
		String password =  request.getParameter(RequestParameterName.PASSWORD);
		String email = request.getParameter(RequestParameterName.EMAIL);
		
		User user = new User(login, password, email);
		
		try {
			DAOFactory factory = DAOFactory.getInstance();
			
			UserDAO userDao = factory.getUserDAO();
			
			userDao.registration(user);
			
			answer = JSPPageName.CATALOG_PAGE;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return answer;
	}

}
