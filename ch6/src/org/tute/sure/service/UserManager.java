package org.tute.sure.service;

import java.util.List;

import org.tute.sure.dao.DAOException;
import org.tute.sure.dao.UserDAO;
import org.tute.sure.entity.User;

public class UserManager {
	private UserDAO userDAO=new UserDAO();
		
	public List<User> list(User user) throws DAOException{
		return (List)userDAO.search(user);
	}

	public User get(int id) throws DAOException {
		return (User) userDAO.get(id);
	}

	public void delete(int id) throws DAOException {
		userDAO.delete(id);
	}

	public void save(User user) throws DAOException {
		userDAO.save(user);		
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}
}
