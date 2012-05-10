package org.tute.sure.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.tute.sure.entity.Department;
import org.tute.sure.entity.Entity;
import org.tute.sure.entity.User;

public class DepartmentDAO extends DAOBase {

	static final String CREATE_Department_SQL = "INSERT INTO Department (name,telephone) VALUES (?, ?)";
	static final String UPDATE_Department_SQL = "UPDATE Department SET name=?, telephone=? WHERE id = ?";
	static final String GET_Department_SQL = "SELECT name, telephone FROM Department WHERE id = ?";
	static final String DELETE_Department_SQL = "DELETE FROM Department WHERE id = ?";
	static final String SEARCH_DepartmentS_SQL = "SELECT id, name, telephone FROM Department WHERE ";

	@Override
	protected PreparedStatement prepareCreateStatement(Connection connection,
			Entity entity) throws SQLException {
		Department department = (Department) entity;
		PreparedStatement pStatement = connection
				.prepareStatement(CREATE_Department_SQL);
		pStatement.setString(1, department.getName());
		pStatement.setString(2, department.getTelephone());
		return pStatement;
	}

	@Override
	protected PreparedStatement prepareUpdateStatement(Connection connection,
			Entity entity) throws SQLException {
		Department department = (Department) entity;
		PreparedStatement pStatement = connection
				.prepareStatement(UPDATE_Department_SQL);
		pStatement.setString(1, department.getName());
		pStatement.setString(2, department.getTelephone());
		pStatement.setInt(3, department.getId());
		return pStatement;

	}

	@Override
	protected Entity populateEntity(ResultSet rs) throws SQLException,
			DAOException {
		Department department = new Department();
		department.setName(rs.getString("name"));
		department.setTelephone(rs.getString("telephone"));

		UserDAO userDao = new UserDAO();
		User user = new User();
		user.setDepartmentId(department.getId());
		department.setUserList((List) userDao.search(user));
		return department;
	}

	@Override
	protected String getGetEntitySQL() {
		return GET_Department_SQL;
	}

	@Override
	protected String getDeleteEntitySQL() {
		return DELETE_Department_SQL;
	}

	@Override
	protected StringBuilder prepareCriteria(Entity entity) {
		StringBuilder criteriaSql = new StringBuilder(512);
		criteriaSql.append(SEARCH_DepartmentS_SQL);
		if (entity != null) {
			Department department = (Department) entity;
			if (department.getId() != null) {
				criteriaSql.append("id =" + department.getId() + " AND ");
			}
			if (department.getName() != null) {
				criteriaSql.append("name LIKE '%" + department.getName()
						+ "%' AND ");
			}
			if (department.getTelephone() != null) {
				criteriaSql.append("telephone LIKE '%"
						+ department.getTelephone() + "%' AND ");
			}
		}
		return criteriaSql;
	}

	@Override
	protected List<Entity> populateEntityList(ResultSet resultSet)
			throws SQLException, DAOException {
		List<Entity> departments = new ArrayList<Entity>();
		while (resultSet.next()) {
			Department department = new Department();
			department.setId(resultSet.getInt("id"));
			department.setName(resultSet.getString("name"));
			department.setTelephone(resultSet.getString("telephone"));

			UserDAO userDao = new UserDAO();
			User user = new User();
			user.setDepartmentId(department.getId());
			department.setUserList((List) userDao.search(user));

			departments.add(department);
		}
		return departments;
	}
}
