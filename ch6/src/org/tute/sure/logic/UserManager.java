package org.tute.sure.logic;


import java.util.List;

import org.tute.sure.hibernatedao.UserDAO;
import org.tute.sure.model.User;

public class UserManager{
	private UserDAO userDAO=new UserDAO();
		
	public List<User> list(User user){
		return (List)userDAO.findAll();
	}

	public User get(int id){
		return (User) userDAO.findById(id);
	}

	public void delete(int id){
		userDAO.delete(userDAO.findById(id));
	}

	public void save(User user){
		userDAO.save(user);		
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}
}
