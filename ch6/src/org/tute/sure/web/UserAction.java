package org.tute.sure.web;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.tute.sure.dao.DAOException;
import org.tute.sure.dao.DepartmentDAO;
import org.tute.sure.logic.DepartmentManager;
import org.tute.sure.logic.UserManager;
import org.tute.sure.model.Department;
import org.tute.sure.model.User;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.inject.Inject;

public class UserAction extends ActionSupport {
	private static final long serialVersionUID = 8685964408635794026L;
	private static final String RELOAD="reload";
	private UserManager userManager;
	private DepartmentManager departmentManager;

	private List<User> userList;
	private Integer id;
	private User user;
	private List<Department> departmentList;
	private List<Integer> checkedIds;
	private Integer departmentId;
	private File photoFile;
	private boolean deletePhoto;
	private static Logger log=Logger.getLogger(UserAction.class);

	public boolean isDeletePhoto() {
		return deletePhoto;
	}

	public void setDeletePhoto(boolean deletePhoto) {
		this.deletePhoto = deletePhoto;
	}

	public String input() throws DAOException{
		if(id!=null){
			user=userManager.get(id);
		}
		else {
			user=new User();
			user.setDepartment(departmentManager.get(departmentId));
		}
		return INPUT;
	}
	
	public String delete() throws DAOException{
		if(id!=null){
			userManager.delete(id);
		}
		return RELOAD;
	}
	
	private Blob getPhotoBlob() throws IOException, SQLException, DAOException{
		InputStream is=new FileInputStream(photoFile);
		BufferedInputStream bis=new BufferedInputStream(is);
		Blob blob=Hibernate.createBlob(bis);
		bis.close();
		return blob;
	}
	public String save() throws DAOException{
		if(photoFile!=null){//有上传图片
			try {
				user.setPhoto(getPhotoBlob());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{//无上传图片
			if(deletePhoto!=true&&user.getId()!=null){//如果界面未选择删除复选框，表示不添加新图片，此时取出旧图片为photo字段赋值，否则，photo为null，则update后将被删除。
				user.setPhoto(userManager.get(user.getId()).getPhoto());
			}
		}
		userManager.save(user);
		departmentId=user.getDepartment().getId();
		if(log.isInfoEnabled()){
			HttpServletRequest reqeust=ServletActionContext.getRequest();
			log.info(reqeust.getRemoteUser()+"保存了用户信息("+user.getId()+","+user.getName()+")");
		}
		return RELOAD;
	}
	
	public String execute() throws DAOException{
		if(departmentId!=null){
			if(user==null){
				user=new User();
			}
			user.setDepartment(departmentManager.get(departmentId));
		}
		return list(user);
	}
	
	public String list(User user) throws DAOException{
		userList=userManager.list(user);
		return SUCCESS;
	}
	
	public String batch(){
		//此处可进行批处理
		return RELOAD;
	}
	
	public void validateSave(){
		if(user.getName()==null||user.getName().trim().equals("")){
			this.addActionError("user's name must be not null!");
		}
		return;
	}
	@Inject("userManager")
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public UserManager getUserManager() {
		return userManager;
	}

	public DepartmentManager getDepartmentManager() {
		return departmentManager;
	}
	
	@Inject("departmentManager")
	public void setDepartmentManager(DepartmentManager departmentManager) {
		this.departmentManager = departmentManager;
	}
	
	
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setDepartmentList(List<Department> departmentList) {
		this.departmentList = departmentList;
	}

	public List<Department> getDepartmentList() throws DAOException {
		DepartmentDAO dao=new DepartmentDAO();
		departmentList=(List)dao.search(null);
		return departmentList;
	}

	public String showPhoto(){
		return "showPhoto";
	}
	
	public InputStream getInputStream() throws DAOException, SQLException{
		User user=userManager.get(id);
		if(user!=null&&user.getPhoto()!=null){
			return user.getPhoto().getBinaryStream();
		}else{
			return null;
		}		
	}
	
	public void setCheckedIds(List<Integer> checkedIds) {
		this.checkedIds = checkedIds;
	}

	public List<Integer> getCheckedIds() {
		return checkedIds;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setPhotoFile(File photoFile) {
		this.photoFile = photoFile;
	}

	public File getPhotoFile() {
		return photoFile;
	}
}