package ua.nure.butorin.SummaryTask4.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.nure.butorin.SummaryTask4.db.bean.UserOrderBean;
import ua.nure.butorin.SummaryTask4.db.entity.Bill;
import ua.nure.butorin.SummaryTask4.db.entity.Order;
import ua.nure.butorin.SummaryTask4.db.entity.User;
import ua.nure.butorin.SummaryTask4.exception.DBException;
import ua.nure.butorin.SummaryTask4.exception.Messages;

public class OrderDAO {

	private DBManager db;

	private static final String SQL_INSERT_ORDER = "INSERT INTO orders(car_id, user_id, passport, driver, price, period, amount, status_id)VALUES(?,?,?,?,?,?,?,?)";
	private static final String SQL_FIND_ORDERS_BY_ORDER_ID = "SELECT * FROM orders WHERE id=?";	
	private static final String SQL_FIND_ORDERS_BY_USER_ID = "SELECT * FROM orders WHERE user_id=?";
	private static final String SQL_UPDATE_ORDER_STATUS = "UPDATE orders SET status_id=? WHERE id=?";
	private static final String SQL_UPDATE_ORDER_STATUS_AND_BILL = "UPDATE orders SET status_id=?, bill=? WHERE id=?";
	private static final String SQL_UPDATE_ORDER_STATUS_AND_BILL_AND_SUM = "UPDATE orders SET status_id=?, bill=?, compensation_sum=? WHERE id=?";
	private static final String SQL_UPDATE_ORDER_STATUS_AND_COMMENTS = "UPDATE orders SET status_id=?, comments=? WHERE id=?";
	private static final String SQL_UPDATE_ORDER_STATUS_BY_BILL = "UPDATE orders SET status_id=? WHERE bill=?";
	private static final String SQL_UPDATE_BILL_STATE = "UPDATE bills SET state=? WHERE id=?";
	private static final String SQL_INSERT_BILL = "INSERT INTO bills(user_id, operation_name, amount, state)VALUES(?,?,?,?)";
	private static final String SQL_FIND_BILL_BY_USER = "SELECT * FROM bills WHERE user_id=?";
	private static final String SQL_FIND_USER_ORDER_BEANS_BY_STATUS_ID = "SELECT o.id, u.first_name, u.last_name, c.brand_id, c.model, c.price, o.period, o.driver, o.comments, o.bill, s.name"
			+ " FROM users u, cars c, orders o, statuses s"
			+ " WHERE o.user_id=u.id AND o.car_id=c.id AND o.status_id=s.id AND o.status_id=?";
	private static final String SQL_FIND_USER_ORDER_BEANS_BY_USER_ID = "SELECT o.id, u.first_name, u.last_name, c.brand_id, c.model, c.price, o.period, o.driver, o.bill, o.comments, s.name"
			+ " FROM users u, cars c, orders o, statuses s"
			+ " WHERE o.user_id=u.id AND o.car_id=c.id AND o.status_id=s.id AND o.user_id=?";
	private static final String SQL_FIND_USER_ORDER_BEANS = "SELECT o.id, u.first_name, u.last_name, c.brand_id, c.model, c.price, o.period, o.driver, o.bill, o.comments, s.name"
			+ " FROM users u, cars c, orders o, statuses s"
			+ " WHERE o.user_id=u.id AND o.car_id=c.id AND o.status_id=s.id";
	
	//add task
	private static final String SQL_FIND_COUNT_ORDERS_BY_USER_ID = "SELECT COUNT(id) FROM orders WHERE user_id=?";
	
	public List<Order> findCountOrdersByUserId(User user) throws DBException {
		List<Order> ordersList = new ArrayList<Order>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			db = DBManager.getInstance();
			con = db.getConnection();
			pstmt = con.prepareStatement(SQL_FIND_COUNT_ORDERS_BY_USER_ID);
			pstmt.setLong(1, user.getId());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ordersList.add(extractOrder(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			db.rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_ORDERS_BY_USER_ID, ex);
		} finally {
			db.close(con, pstmt, rs);
		}
		return ordersList;
	}	

	public Order createOrder(Order order) throws DBException {
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			db = DBManager.getInstance();
			con = db.getConnection();
			pstmt = con.prepareStatement(SQL_INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);
			pstmt.setLong(1, order.getCarId());
			pstmt.setLong(2, order.getUserId());
			pstmt.setString(3, order.getPassport());
			pstmt.setBoolean(4, order.isDriver());
			pstmt.setInt(5, order.getPrice());
			pstmt.setInt(6, order.getPeriod());
			pstmt.setInt(7, order.getAmount());
			pstmt.setInt(8, order.getStatusId());
			pstmt.executeUpdate();
			ResultSet generatedKeys = pstmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				order.setId(generatedKeys.getLong(1));
			}
			con.commit();
		} catch (SQLException ex) {
			db.rollback(con);
			throw new DBException(Messages.ERR_CANNOT_CREATE_ORDER, ex);
		} finally {
			db.close(con, pstmt);
		}
		return order;
	}

	public void updateOrderStatus(int orderStatus, long orderId) throws DBException {
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			db = DBManager.getInstance();
			con = db.getConnection();
			pstmt = con.prepareStatement(SQL_UPDATE_ORDER_STATUS);
			int counter = 1;
			pstmt.setInt(counter++, orderStatus);
			pstmt.setLong(counter, orderId);
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException ex) {
			db.rollback(con);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_ORDER_STATUS, ex);
		} finally {
			db.close(con, pstmt);
		}
	}

	public void updateOrderStatusByBill(int orderStatus, long bill) throws DBException {
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			db = DBManager.getInstance();
			con = db.getConnection();
			pstmt = con.prepareStatement(SQL_UPDATE_ORDER_STATUS_BY_BILL);
			int counter = 1;
			pstmt.setInt(counter++, orderStatus);
			pstmt.setLong(counter, bill);
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException ex) {
			db.rollback(con);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_ORDER_STATUS, ex);
		} finally {
			db.close(con, pstmt);
		}
	}

	public void updateOrderStatus(int orderStatus, long orderId, long bill) throws DBException {
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			db = DBManager.getInstance();
			con = db.getConnection();
			pstmt = con.prepareStatement(SQL_UPDATE_ORDER_STATUS_AND_BILL);
			int counter = 1;
			pstmt.setInt(counter++, orderStatus);
			pstmt.setLong(counter++, bill);
			pstmt.setLong(counter, orderId);
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException ex) {
			db.rollback(con);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_ORDER_STATUS, ex);
		} finally {
			db.close(con, pstmt);
		}
	}

	public void updateOrderStatus(int orderStatus, long orderId, long bill, int compensationSum) throws DBException {
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			db = DBManager.getInstance();
			con = db.getConnection();
			pstmt = con.prepareStatement(SQL_UPDATE_ORDER_STATUS_AND_BILL_AND_SUM);
			int counter = 1;
			pstmt.setInt(counter++, orderStatus);
			pstmt.setLong(counter++, bill);
			pstmt.setInt(counter++, compensationSum);
			pstmt.setLong(counter, orderId);
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException ex) {
			db.rollback(con);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_ORDER_STATUS, ex);
		} finally {
			db.close(con, pstmt);
		}
	}

	public void updateOrder(int orderStatus, long orderId, String comments) throws DBException {
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			db = DBManager.getInstance();
			con = db.getConnection();
			pstmt = con.prepareStatement(SQL_UPDATE_ORDER_STATUS_AND_COMMENTS);
			int counter = 1;
			pstmt.setInt(counter++, orderStatus);
			pstmt.setString(counter++, comments);
			pstmt.setLong(counter, orderId);
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException ex) {
			db.rollback(con);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_ORDER_STATUS, ex);
		} finally {
			db.close(con, pstmt);
		}
	}

	public Order findOrder(int id) throws DBException {
		Order order = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			db = DBManager.getInstance();
			con = db.getConnection();
			pstmt = con.prepareStatement(SQL_FIND_ORDERS_BY_ORDER_ID);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				order = extractOrder(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			db.rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_ORDER_ID, ex);
		} finally {
			db.close(con, pstmt, rs);
		}
		return order;
	}

	public List<Order> findOrdersByUserId(Long userId) throws DBException {
		List<Order> ordersList = new ArrayList<Order>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			db = DBManager.getInstance();
			con = db.getConnection();
			pstmt = con.prepareStatement(SQL_FIND_ORDERS_BY_USER_ID);
			pstmt.setLong(1, userId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ordersList.add(extractOrder(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			db.rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_ORDERS_BY_USER_ID, ex);
		} finally {
			db.close(con, pstmt, rs);
		}
		return ordersList;
	}
	
	

	public List<UserOrderBean> findUserOrderBeans() throws DBException {
		List<UserOrderBean> userOrderBeanList = new ArrayList<UserOrderBean>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			db = DBManager.getInstance();
			con = db.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_USER_ORDER_BEANS);
			while (rs.next()) {
				userOrderBeanList.add(extractUserOrderBean(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			db.rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_ORDER_BEANS, ex);
		} finally {
			db.close(con, stmt, rs);
		}
		return userOrderBeanList;
	}

	public List<UserOrderBean> findUserOrderBeans(int statusId) throws DBException {
		List<UserOrderBean> userOrderBeanList = new ArrayList<UserOrderBean>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			db = DBManager.getInstance();
			con = db.getConnection();
			pstmt = con.prepareStatement(SQL_FIND_USER_ORDER_BEANS_BY_STATUS_ID);
			pstmt.setInt(1, statusId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				userOrderBeanList.add(extractUserOrderBean(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			db.rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_ORDER_BEANS_BY_STATUS_ID, ex);
		} finally {
			db.close(con, pstmt, rs);
		}
		return userOrderBeanList;
	}

	public List<UserOrderBean> findUserOrderBeansByUserId(long userId) throws DBException {
		List<UserOrderBean> userOrderBeanList = new ArrayList<UserOrderBean>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			db = DBManager.getInstance();
			con = db.getConnection();
			pstmt = con.prepareStatement(SQL_FIND_USER_ORDER_BEANS_BY_USER_ID);
			pstmt.setLong(1, userId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				userOrderBeanList.add(extractUserOrderBean(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			db.rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_ORDER_BEANS_BY_USER_ID, ex);
		} finally {
			db.close(con, pstmt, rs);
		}
		return userOrderBeanList;
	}

	public Bill createBill(Bill bill) throws DBException {
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			db = DBManager.getInstance();
			con = db.getConnection();
			pstmt = con.prepareStatement(SQL_INSERT_BILL, Statement.RETURN_GENERATED_KEYS);
			pstmt.setLong(1, bill.getUserId());
			pstmt.setString(2, bill.getOperationName());
			pstmt.setInt(3, bill.getAmount());
			pstmt.setBoolean(4, bill.isState());
			pstmt.executeUpdate();
			ResultSet generatedKeys = pstmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				bill.setId(generatedKeys.getLong(1));
			}
			con.commit();
		} catch (SQLException ex) {
			db.rollback(con);
			throw new DBException(Messages.ERR_CANNOT_CREATE_BILL, ex);
		} finally {
			db.close(con, pstmt);
		}
		return bill;
	}

	public void updateBillState(boolean state, long id) throws DBException {
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			db = DBManager.getInstance();
			con = db.getConnection();
			pstmt = con.prepareStatement(SQL_UPDATE_BILL_STATE);
			int counter = 1;
			pstmt.setBoolean(counter++, state);
			pstmt.setLong(counter, id);
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException ex) {
			db.rollback(con);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_BILL_STATE, ex);
		} finally {
			db.close(con, pstmt);
		}
	}

	public List<Bill> findBills(User user) throws DBException {
		List<Bill> billList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			db = DBManager.getInstance();
			con = db.getConnection();
			pstmt = con.prepareStatement(SQL_FIND_BILL_BY_USER);
			pstmt.setLong(1, user.getId());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				billList.add(extractBill(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			db.rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_BILL_BY_USER, ex);
		} finally {
			db.close(con, pstmt, rs);
		}
		return billList;
	}

	private Order extractOrder(ResultSet rs) throws SQLException {
		Order order = new Order();
		order.setId(rs.getLong(Fields.ENTITY_ID));
		order.setBill(rs.getLong(Fields.ORDER_BILL));
		order.setCarId(rs.getLong(Fields.ORDER_CAR_ID));
		order.setUserId(rs.getLong(Fields.ORDER_USER_ID));
		order.setPassport(rs.getString(Fields.ORDER_PASSPORT));
		order.setDriver(rs.getBoolean(Fields.ORDER_DRIVER));
		order.setPrice(rs.getInt(Fields.ORDER_PRICE));
		order.setPeriod(rs.getInt(Fields.ORDER_PERIOD));
		order.setAmount(rs.getInt(Fields.ORDER_AMOUNT));
		order.setStatusId(rs.getInt(Fields.ORDER_STATUS_ID));
		order.setCompensationSum(rs.getInt(Fields.ORDER_COMPENSATION_SUM));
		order.setComments(rs.getString(Fields.ORDER_COMMENTS));
		return order;
	}

	private Bill extractBill(ResultSet rs) throws SQLException {
		Bill bill = new Bill();
		bill.setId(rs.getLong(Fields.ENTITY_ID));
		bill.setUserId(rs.getLong(Fields.BILL_USER_ID));
		bill.setOperationName(rs.getString(Fields.BILL_OPERATION_NAME));
		bill.setAmount(rs.getInt(Fields.BILL_AMOUNT));
		bill.setState(rs.getBoolean(Fields.BILL_STATE));
		return bill;
	}

	/**
	 * Extracts a user order bean from the result set.
	 * 
	 * @param rs Result set from which a user order bean will be extracted.
	 * @return UserOrderBean object
	 */
	private UserOrderBean extractUserOrderBean(ResultSet rs) throws SQLException {
		UserOrderBean userOrderBean = new UserOrderBean();
		userOrderBean.setId(rs.getLong(Fields.ENTITY_ID));
		userOrderBean.setOrderId(rs.getLong(Fields.USER_ORDER_BEAN_ORDER));
		userOrderBean.setUserFirstName(rs.getString(Fields.USER_ORDER_BEAN_USER_FIRST_NAME));
		userOrderBean.setUserLastName(rs.getString(Fields.USER_ORDER_BEAN_USER_LAST_NAME));
		userOrderBean.setCarBrandId(rs.getInt(Fields.USER_ORDER_BEAN_CAR_BRAND_ID));
		userOrderBean.setCarModel(rs.getString(Fields.USER_ORDER_BEAN_CAR_MODEL));
		userOrderBean.setDriver(rs.getBoolean(Fields.USER_ORDER_BEAN_DRIVER));
		userOrderBean.setPrice(rs.getInt(Fields.USER_ORDER_BEAN_PRICE));
		userOrderBean.setRentPeriod(rs.getInt(Fields.USER_ORDER_BEAN_RENT_PERIOD));
		userOrderBean.setOrderBill(rs.getInt(Fields.USER_ORDER_BEAN_ORDER_BILL_ID));
		userOrderBean.setOrderStatusName(rs.getString(Fields.USER_ORDER_BEAN_ORDER_STATUS_NAME));
		userOrderBean.setOrderComments(rs.getString(Fields.USER_ORDER_BEAN_ORDER_COMMENTS));
		return userOrderBean;
	}
}
