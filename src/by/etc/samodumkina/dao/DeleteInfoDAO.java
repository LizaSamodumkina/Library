package by.etc.samodumkina.dao;

import by.etc.samodumkina.dao.exception.DAOException;
import by.etc.samodumkina.specification.Specification;

public interface DeleteInfoDAO {
	public void delete(Specification specification) throws DAOException;
}
