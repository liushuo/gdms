package org.tute.sure.service;

import java.util.List;

import org.tute.sure.dao.DAOException;
import org.tute.sure.dao.DepartmentDAO;
import org.tute.sure.entity.Department;

public class DepartmentManager {
	private DepartmentDAO departmentDAO=new DepartmentDAO();
		
	public List<Department> list(Department department) throws DAOException{
		return (List)departmentDAO.search(department);
	}

	public Department get(int id) throws DAOException {
		return (Department) departmentDAO.get(id);
	}

	public void delete(int id) throws DAOException {
		departmentDAO.delete(id);
	}

	public void save(Department department) throws DAOException {
		departmentDAO.save(department);		
	}
	public DepartmentDAO getDepartmentDAO() {
		return departmentDAO;
	}

	public void setDepartmentDAO(DepartmentDAO departmentDAO) {
		this.departmentDAO = departmentDAO;
	}
}
