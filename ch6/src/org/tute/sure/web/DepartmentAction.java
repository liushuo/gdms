package org.tute.sure.web;

import java.util.List;

import org.tute.sure.dao.DAOException;
import org.tute.sure.model.Department;
import org.tute.sure.logic.DepartmentManager;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.inject.Inject;

public class DepartmentAction extends ActionSupport {

	private static final long serialVersionUID = -3368114702662963524L;

	private static final String RELOAD="reload";
	private DepartmentManager departmentManager;
	private List<Department> departmentList;
	private Integer id;
	private Department department;

	public String input() throws DAOException{
		if(id!=null){
			department=departmentManager.get(id);
		}else{
			department=new Department();
		}
		return INPUT;
	}

	public String delete() throws DAOException{
		if(id!=null){
			departmentManager.delete(id);
		}
		return RELOAD;
	}
	
	public String save() throws DAOException{
		departmentManager.save(department);
		return RELOAD;
	}
	
	public String execute() throws DAOException{
		return list(department);
	}
	
	public String list(Department department) throws DAOException{
		setDepartmentList(departmentManager.list(department));
		return SUCCESS;
	}
	
	public DepartmentManager getDepartmentManager() {
		return departmentManager;
	}
	
	@Inject("departmentManager")
	public void setDepartmentManager(DepartmentManager departmentManager) {
		this.departmentManager = departmentManager;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setDepartmentList(List<Department> departmentList) {
		this.departmentList = departmentList;
	}

	public List<Department> getDepartmentList() {
		return departmentList;
	}
}