package by.etc.samodumkina.dao;

import java.util.List;

import by.etc.samodumkina.dao.exception.DAOException;
import by.etc.samodumkina.specification.Specification;

public interface TakeInfoDAO<T> {
	public List<T> read(Specification specification) throws DAOException;
}
