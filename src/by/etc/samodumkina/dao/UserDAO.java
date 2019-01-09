package by.etc.samodumkina.dao;

import by.etc.samodumkina.bean.User;
import by.etc.samodumkina.dao.exception.DAOException;

public interface UserDAO {
	public boolean signIn(User user) throws DAOException;
	public void registration(User user) throws DAOException;
	public boolean isAdmin(User user) throws DAOException;
}
