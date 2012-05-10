package org.tute.sure.entity;

import java.sql.Blob;
import java.util.Date;

public class User extends Entity {
	private String name;
	private String password;
	private Date birthday;
	private Double income;
	private Boolean gender;
	private Integer departmentId;
	private Blob photo;

	public User() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public Boolean getGender() {
		return gender;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setPhoto(Blob photo) {
		this.photo = photo;
	}

	public Blob getPhoto() {
		return photo;
	}
}
