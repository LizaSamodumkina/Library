package by.etc.samodumkina.dao.impl;

import java.util.List;

import by.etc.samodumkina.dao.AddInfoDAO;
import by.etc.samodumkina.dao.exception.DAOException;

public class SQLCloseOrder implements AddInfoDAO<String>{

	@Override
	public boolean add(List<String> data) throws DAOException {

		//transaction to close order
		
		return false;
	}

}
