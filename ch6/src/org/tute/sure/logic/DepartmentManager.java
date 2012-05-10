package org.tute.sure.logic;

import java.util.List;

import org.tute.sure.hibernatedao.DepartmentDAO;
import org.tute.sure.model.Department;

public class DepartmentManager {
	private DepartmentDAO departmentDAO=new DepartmentDAO();
		
	public List<Department> list(Department department){
		return (List)departmentDAO.findAll();
	}

	public Department get(int id){
		return (Department) departmentDAO.findById(id);
	}

	public void delete(int id){
		departmentDAO.delete(departmentDAO.findById(id));
	}

	public void save(Department department){
		departmentDAO.save(department);		
	}
	public DepartmentDAO getDepartmentDAO() {
		return departmentDAO;
	}

	public void setDepartmentDAO(DepartmentDAO departmentDAO) {
		this.departmentDAO = departmentDAO;
	}
}
