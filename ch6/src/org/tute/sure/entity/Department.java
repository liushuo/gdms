package org.tute.sure.entity;

import java.util.List;

public class Department extends Entity {
	private String name;
	private String telephone;
	private List<User> userList;

	public Department() {
		setUserList(new java.util.LinkedList<User>());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public List<User> getUserList() {
		return userList;
	}

}
