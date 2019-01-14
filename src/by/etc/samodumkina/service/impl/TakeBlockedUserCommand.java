package by.etc.samodumkina.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.etc.samodumkina.bean.User;
import by.etc.samodumkina.dao.exception.DAOException;
import by.etc.samodumkina.dao.factory.DAOFactory;
import by.etc.samodumkina.service.Command;
import by.etc.samodumkina.service.exception.ServiceException;
import by.etc.samodumkina.specification.Specification;
import by.etc.samodumkina.specification.impl.TakeAllBlockedUsersSpecification;

public class TakeBlockedUserCommand implements Command<User>{

	@Override
	public List<User> execute(HttpServletRequest request) throws ServiceException {
		List<User> users = null;
		
		try {
			Specification specification = new TakeAllBlockedUsersSpecification();
			
			users = DAOFactory.getInstance().takeReadBlockedUsers().read(specification);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return users;
	}

}
