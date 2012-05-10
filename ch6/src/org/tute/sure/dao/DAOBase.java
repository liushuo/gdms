package org.tute.sure.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.tute.sure.entity.Entity;

public abstract class DAOBase implements DAO{

	public Connection getConnection() throws DAOException {
		Context ctx = null;
		DataSource dataSource = null;
		Connection connection = null;
		try {
			ctx = new InitialContext();
			//通过JNDI获得DataSource
			dataSource = (DataSource) ctx.lookup("java:/comp/env/jdbc/mysql");
		} catch (NamingException ne) {
			ne.printStackTrace();
		}
		if (dataSource != null) {
			try {
				//容器获得缓冲池连接
				connection = dataSource.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException();
			}
		}
		return connection;
	}

	public void save(Entity entity) throws DAOException {
		if(entity.getId()==null){
			create(entity);
		}else{
			update(entity);
		}		
	}
	public void create(Entity entity) throws DAOException {
	    Connection connection = null;
	    PreparedStatement pStatement = null;
	    try {
	        connection = getConnection();
	        // Prepare a statement to insert a record
	        pStatement=prepareCreateStatement(connection,entity);
	        pStatement.executeUpdate();
	        pStatement.close();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	        throw new DAOException();
	    } finally {
	        try {
	            connection.close();
	        } catch (SQLException ex) {
	            throw new DAOException();
	        }
	    }
	}

	protected abstract PreparedStatement prepareCreateStatement(Connection connection, Entity entity) throws SQLException;

	public void update(Entity entity) throws DAOException {
	    Connection connection = null;
	    PreparedStatement pStatement = null;
	    try {
	        connection = getConnection();
	        pStatement=prepareUpdateStatement(connection,entity);
	        pStatement.executeUpdate();
	        pStatement.close();
	    } catch (SQLException e) {
	    	e.printStackTrace();
	        throw new DAOException();
	    } finally {
	        try {
	            connection.close();
	        } catch (SQLException ex) {
	        }
	    }
	}

	protected abstract PreparedStatement prepareUpdateStatement(Connection connection, Entity entity) throws SQLException;

	public Entity get(int id) throws DAOException {
	    Connection connection = null;
	    PreparedStatement pStatement = null;
	    ResultSet rs = null;
	    Entity entity=null;
	    try {
	        connection = getConnection();
	        pStatement = connection.prepareStatement(getGetEntitySQL());
	        pStatement.setInt(1, id);
	        rs = pStatement.executeQuery();
	        if (rs.next()) {
	        	entity=populateEntity(rs);
	        	entity.setId(id);		
	        }
	        rs.close();
	        pStatement.close();
	    } catch (SQLException ex) {
	        throw new DAOException();
	    } finally {
	        try {
	            connection.close();
	        } catch (SQLException ex) {
	        }
	    }
	    return entity;
	}
	
	protected abstract String getGetEntitySQL();

	protected abstract Entity populateEntity(ResultSet rs) throws SQLException, DAOException;

	public void delete(int id) throws DAOException {
	    Connection connection = null;
	    PreparedStatement pStatement = null;
	    try {
	        connection = getConnection();
	        pStatement = connection.prepareStatement(getDeleteEntitySQL());
	        pStatement.setInt(1, id);
	        pStatement.executeUpdate();
	        pStatement.close();
	    } catch (SQLException e) {
	        throw new DAOException();
	    } finally {
	        try {
	            connection.close();
	        } catch (SQLException ex) {
	        }
	    }
	}
	protected abstract String getDeleteEntitySQL();

	public List<Entity> search(Entity entity) throws DAOException {
	
	    List<Entity> departments = null;
	    Connection connection = null;
	    Statement statement = null;
	    ResultSet resultSet = null;
	
	    // Build the search criterias
	    StringBuilder criteriaSql = prepareCriteria(entity);
	    
	    // Remove unused 'And' & 'WHERE'
	    if (criteriaSql.substring(criteriaSql.length() - 5).equals(" AND "))
	        criteriaSql.delete(criteriaSql.length() - 5,
	                criteriaSql.length() - 1);
	    if (criteriaSql.substring(criteriaSql.length() - 7).equals(" WHERE "))
	        criteriaSql.delete(criteriaSql.length() - 7,
	                criteriaSql.length() - 1);
	
	    try {
	        connection = getConnection();
	        statement = connection.createStatement();
	        resultSet = statement.executeQuery(criteriaSql.toString());
	        
	        departments=populateEntityList(resultSet);
	        resultSet.close();
	        statement.close();
	    } catch (SQLException e) {
	    	e.printStackTrace();
	        throw new DAOException();
	    } finally {
	        try {
	            connection.close();
	        } catch (SQLException ex) {
	        }
	    }
	    return departments;
	}

	protected abstract List<Entity> populateEntityList(ResultSet resultSet) throws SQLException, DAOException;

	protected abstract StringBuilder prepareCriteria(Entity entity);
}
