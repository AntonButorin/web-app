package ua.nure.butorin.SummaryTask4.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import ua.nure.butorin.SummaryTask4.exception.DBException;
import ua.nure.butorin.SummaryTask4.exception.Messages;

/**
 * DB manager for MySQL DB
 * 
 * @author A.Butorin
 * 
 */

public final class DBManager {

	private static final Logger LOG = Logger.getLogger(DBManager.class);

	protected static DBManager instance;
	private DataSource ds;

	public static synchronized DBManager getInstance() throws DBException {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

	private DBManager() throws DBException {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/rentcardb");
			LOG.trace("Data source --> " + ds);
		} catch (NamingException ex) {
//			LOG.error(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
		}
	}

	public Connection getConnection() throws DBException {
		Connection con = null;
		try {
			con = ds.getConnection();
		} catch (SQLException ex) {
//			LOG.error(Messages.ERR_CANNOT_OBTAIN_CONNECTION, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_CONNECTION, ex);
		}
		return con;
	}

	protected void rollback(Connection con) throws DBException {
		if (con != null) {
			try {
				con.rollback();
			} catch (SQLException ex) {
//				LOG.error(Messages.ERR_CANNOT_ROLLBACK_TRANSACTION, ex);
				throw new DBException(Messages.ERR_CANNOT_ROLLBACK_TRANSACTION, ex);
			}
		}
	}

	protected void close(Connection con) throws DBException {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException ex) {
//				LOG.error(Messages.ERR_CANNOT_CLOSE_CONNECTION, ex);
				throw new DBException(Messages.ERR_CANNOT_CLOSE_CONNECTION, ex);
			}
		}
	}

	protected void close(Statement stmt) throws DBException {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException ex) {
//				LOG.error(Messages.ERR_CANNOT_CLOSE_STATEMENT, ex);
				throw new DBException(Messages.ERR_CANNOT_CLOSE_STATEMENT, ex);
			}
		}
	}

	protected void close(ResultSet rs) throws DBException {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) {
//				LOG.error(Messages.ERR_CANNOT_CLOSE_RESULTSET, ex);
				throw new DBException(Messages.ERR_CANNOT_CLOSE_RESULTSET, ex);
			}
		}
	}

	protected void close(Connection con, Statement stmt, ResultSet rs) throws DBException {
		close(rs);
		close(stmt);
		close(con);
	}

	protected void close(Connection con, Statement stmt) throws DBException {
		close(stmt);
		close(con);
	}
}