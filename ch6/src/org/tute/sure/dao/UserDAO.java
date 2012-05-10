package org.tute.sure.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.tute.sure.entity.Department;
import org.tute.sure.entity.Entity;
import org.tute.sure.entity.User;

public class UserDAO extends DAOBase {

	private static final String SEARCH_UserS_SQL = "SELECT id, name, password,birthday,income,gender,departmentId FROM users WHERE ";
	private static final String DELETE_User_SQL = "DELETE FROM Users WHERE id = ?";
	private static final String UPDATE_User_SQL_WITH_PHOTO = "UPDATE Users SET name=?,password=?,birthday=?,income=?,gender=?,departmentId=?,photo=? WHERE id = ?";
	private static final String UPDATE_User_SQL = "UPDATE Users SET name=?,password=?,birthday=?,income=?,gender=?,departmentId=? WHERE id = ?";
	private static final String CREATE_User_SQL = "INSERT INTO Users (name,password,birthday,income,gender,departmentId,photo) VALUES (?, ?, ?, ? ,? ,?,? )";
	private static final String GET_User_SQL = "SELECT * FROM Users WHERE id = ?";

	@Override
	protected PreparedStatement prepareCreateStatement(Connection connection,
			Entity entity) throws SQLException {
		User user = (User) entity;
		PreparedStatement pStatement = connection
				.prepareStatement(CREATE_User_SQL);
		pStatement.setString(1, user.getName());
		pStatement.setString(2, user.getPassword());
		pStatement.setDate(3, new java.sql.Date(user.getBirthday().getTime()));
		pStatement.setDouble(4, user.getIncome());
		pStatement.setBoolean(5, user.getGender());
		pStatement.setInt(6, user.getDepartmentId());
		pStatement.setBlob(7, user.getPhoto());
		return pStatement;
	}

	@Override
	protected PreparedStatement prepareUpdateStatement(Connection connection,
			Entity entity) throws SQLException {
		User user = (User) entity;
		PreparedStatement pStatement =null;
//		if(user.getPhoto()==null){
//			pStatement = connection.prepareStatement(UPDATE_User_SQL);
//		}else{
			pStatement = connection.prepareStatement(UPDATE_User_SQL_WITH_PHOTO);
//		}
		pStatement.setString(1, user.getName());
		pStatement.setString(2, user.getPassword());
		pStatement.setDate(3, new java.sql.Date(user.getBirthday().getTime()));
		pStatement.setDouble(4, user.getIncome());
		pStatement.setBoolean(5, user.getGender());
		pStatement.setInt(6, user.getDepartmentId());
		
//		if(user.getPhoto()!=null){
			pStatement.setBlob(7, user.getPhoto());
			pStatement.setInt(8, user.getId());
//		}
//		else{
//			pStatement.setInt(7, user.getId());
//		}
		
		return pStatement;
	}

	@Override
	protected String getGetEntitySQL() {
		return GET_User_SQL;
	}

	@Override
	protected Entity populateEntity(ResultSet rs) throws SQLException {
		User user = new User();
		user.setName(rs.getString("name"));
		user.setBirthday(rs.getDate("birthday"));
		user.setPassword(rs.getString("password"));
		user.setIncome(rs.getDouble("income"));
		user.setGender(rs.getBoolean("gender"));
		user.setDepartmentId(rs.getInt("departmentId"));
		user.setPhoto(rs.getBlob("photo"));
		return user;
	}

	@Override
	protected String getDeleteEntitySQL() {
		return DELETE_User_SQL;
	}

	@Override
	protected List<Entity> populateEntityList(ResultSet rs)
			throws SQLException, DAOException {
		List<Entity> users = new ArrayList<Entity>();
		while (rs.next()) {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			user.setBirthday(rs.getDate("birthday"));
			user.setPassword(rs.getString("password"));
			user.setIncome(rs.getDouble("income"));
			user.setGender(rs.getBoolean("gender"));
			user.setDepartmentId(rs.getInt("departmentId"));

			users.add(user);
		}
		return users;
	}

	@Override
	protected StringBuilder prepareCriteria(Entity entity) {
		StringBuilder criteriaSql = new StringBuilder(512);
		User criteria = (User) entity;
		criteriaSql.append(SEARCH_UserS_SQL);
		if (entity != null) {
			if (criteria.getId() != null) {
				criteriaSql.append("id =" + criteria.getId() + " AND ");
			}
			if (criteria.getName() != null) {
				criteriaSql.append("name LIKE '%" + criteria.getName() + "%' AND ");
			}
			if (criteria.getPassword() != null) {
				criteriaSql.append("password LIKE '%" + criteria.getPassword()
						+ "%' AND ");
			}
			if (criteria.getBirthday() != null) {
				criteriaSql.append("birthday = '"
						+ new SimpleDateFormat("yyyy-MM-dd").format(criteria
								.getBirthday()) + "' AND ");
			}
			if (criteria.getGender() != null) {
				criteriaSql.append("gender = " + criteria.getGender() + " AND ");
			}
			if (criteria.getDepartmentId() != null) {
				criteriaSql.append("departmentId = " + criteria.getDepartmentId()
						+ " AND ");
			}
		}
		return criteriaSql;
	}
}