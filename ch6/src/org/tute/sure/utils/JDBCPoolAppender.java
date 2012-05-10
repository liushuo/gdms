package org.tute.sure.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.jdbc.JDBCAppender;
import org.apache.log4j.spi.LoggingEvent;

public class JDBCPoolAppender extends JDBCAppender {
	@Override
	protected Connection getConnection() throws SQLException {
		Context ctx = null;
		DataSource dataSource = null;
		Connection connection = null;
		try {
			ctx = new InitialContext();
			// 通过JNDI获得DataSource
			dataSource = (DataSource) ctx.lookup("java:/comp/env/jdbc/mysql");
		} catch (NamingException ne) {
			ne.printStackTrace();
		}
		if (dataSource != null) {
			connection = dataSource.getConnection();
		}
		return connection;
	}

	@Override
	protected void closeConnection(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void execute(String sql) throws SQLException {
		Connection con = null;
		Statement stmt = null;
		try {
			con = getConnection();

			stmt = con.createStatement();
			//stmt.executeUpdate(DBUtil.fixSqlFieldValue(sql));
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			if (stmt != null)
				stmt.close();
			throw e;
		}
		stmt.close();
		closeConnection(con);
	}
}
