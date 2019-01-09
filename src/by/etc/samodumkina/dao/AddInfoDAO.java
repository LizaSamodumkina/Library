package by.etc.samodumkina.dao;

import java.util.List;

import by.etc.samodumkina.dao.exception.DAOException;

public interface AddInfoDAO<T> {
	public boolean add(List<T> data) throws DAOException;
}
