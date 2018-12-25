package by.etc.samodumkina.dao;

import java.util.List;

import by.etc.samodumkina.dao.exception.DAOException;

public interface AddInfoDAO<T> {
	public void add(List<T> data) throws DAOException;
}
