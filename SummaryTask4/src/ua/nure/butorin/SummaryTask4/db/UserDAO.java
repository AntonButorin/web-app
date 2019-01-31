package ua.nure.butorin.SummaryTask4.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.nure.butorin.SummaryTask4.db.entity.User;
import ua.nure.butorin.SummaryTask4.exception.DBException;
import ua.nure.butorin.SummaryTask4.exception.Messages;

public class UserDAO {
	private DBManager db;

	public static final String SQL_INSERT_USER = "INSERT INTO users(login, password, first_name, last_name, role_id, block)VALUES(?,?,?,?,?,?)";
	private static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";
	private static final String SQL_FIND_USERS_BY_ROLE_ID = "SELECT * FROM users WHERE role_id=?";
	private static final String SQL_UPDATE_USER = "UPDATE users SET password=?, first_name=?, last_name=? WHERE id=?";
	public static final String SQL_UPDATE_USER_STATE = "UPDATE users SET block=? WHERE id=?";

	public User findUserByLogin(String login) throws DBException {
		User user = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			db = DBManager.getInstance();
			con = db.getConnection();
			pstmt = con.prepareStatement(SQL_FIND_USER_BY_LOGIN);
			pstmt.setString(1, login);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = extractUser(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			db.rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, ex);
		} finally {
			db.close(con, pstmt, rs);
		}
		return user;
	}

	public User createUser(User user) throws DBException {
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			db = DBManager.getInstance();
			con = db.getConnection();
			pstmt = con.prepareStatement(SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, user.getLogin());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getFirstName());
			pstmt.setString(4, user.getLastName());
			pstmt.setLong(5, user.getRoleId());
			pstmt.setBoolean(6, user.isBlock());
			pstmt.executeUpdate();
			ResultSet generatedKeys = pstmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				user.setId(generatedKeys.getLong(1));
			}
			con.commit();
		} catch (SQLException ex) {
			db.rollback(con);
			throw new DBException(Messages.ERR_CANNOT_CREATE_USER, ex);
		} finally {
			db.close(con, pstmt);
		}
		return user;
	}

	public List<User> findUsersByRoleId(int roleId) throws DBException {
		List<User> listUsers = new ArrayList<User>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			db = DBManager.getInstance();
			con = db.getConnection();
			pstmt = con.prepareStatement(SQL_FIND_USERS_BY_ROLE_ID);
			pstmt.setInt(1, roleId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				listUsers.add(extractUser(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			db.rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USERS_BY_ROLE_ID, ex);
		} finally {
			db.close(con, pstmt, rs);
		}
		return listUsers;
	}

	public void updateUserState(boolean userState, long userId) throws DBException {
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			db = DBManager.getInstance();
			con = db.getConnection();
			pstmt = con.prepareStatement(SQL_UPDATE_USER_STATE);
			int counter = 1;
			pstmt.setBoolean(counter++, userState);
			pstmt.setLong(counter, userId);
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException ex) {
			db.rollback(con);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_STATE_USER, ex);
		} finally {
			db.close(con, pstmt);
		}
	}

	public void updateUser(User user) throws DBException {
		Connection con = null;
		try {
			db = DBManager.getInstance();
			con = db.getConnection();
			updateUser(con, user);
			con.commit();
		} catch (SQLException ex) {
			db.rollback(con);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_USER, ex);
		} finally {
			db.close(con);
		}
	}

	private void updateUser(Connection con, User user) throws SQLException, DBException {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL_UPDATE_USER);
			int k = 1;
			pstmt.setString(k++, user.getPassword());
			pstmt.setString(k++, user.getFirstName());
			pstmt.setString(k++, user.getLastName());
			pstmt.setLong(k, user.getId());
			pstmt.executeUpdate();
		} finally {
			db.close(pstmt);
		}
	}

	private User extractUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getLong(Fields.ENTITY_ID));
		user.setLogin(rs.getString(Fields.USER_LOGIN));
		user.setPassword(rs.getString(Fields.USER_PASSWORD));
		user.setFirstName(rs.getString(Fields.USER_FIRST_NAME));
		user.setLastName(rs.getString(Fields.USER_LAST_NAME));
		user.setRoleId((int) rs.getLong(Fields.USER_ROLE_ID));
		user.setBlock(rs.getBoolean(Fields.USER_BLOCK_STATE));
		return user;
	}
}
