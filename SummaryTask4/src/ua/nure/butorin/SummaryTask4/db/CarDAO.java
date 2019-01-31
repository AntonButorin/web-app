package ua.nure.butorin.SummaryTask4.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.nure.butorin.SummaryTask4.db.entity.Car;
import ua.nure.butorin.SummaryTask4.db.entity.CarBrand;
import ua.nure.butorin.SummaryTask4.exception.DBException;
import ua.nure.butorin.SummaryTask4.exception.Messages;

public class CarDAO {

	private DBManager db;

	public static final String SQL_INSERT_CAR = "INSERT INTO cars(brand_id, model, category_id, seat_amount, fuel_id, air_condition, automatic_transmission, price, guarantee_amount)VALUES(?,?,?,?,?,?,?,?,?)";
	private static final String SQL_UPDATE_CAR = "UPDATE cars SET fuel_id=?, price=?, guarantee_amount=? WHERE id=?";
	private static final String SQL_FIND_CAR_BY_ID = "SELECT * FROM cars WHERE id=?";
	private static final String SQL_FIND_CARS_BY_CATEGORY_ID = "SELECT * FROM cars WHERE category_id=?";
	private static final String SQL_FIND_CARS_BY_BRAND_ID = "SELECT * FROM cars WHERE brand_id=?";
	private static final String SQL_FIND_ALL_CARS = "SELECT * FROM cars";
	private static final String SQL_FIND_ALL_BRANDS = "SELECT * FROM brands";
	private static final String SQL_DELETE_CAR = "DELETE FROM cars WHERE id=?";

	public Car createCar(Car car) throws DBException {
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			db = DBManager.getInstance();
			con = db.getConnection();
			pstmt = con.prepareStatement(SQL_INSERT_CAR, Statement.RETURN_GENERATED_KEYS);
			pstmt.setLong(1, car.getBrandId());
			pstmt.setString(2, car.getModel());
			pstmt.setLong(3, car.getCategoryId());
			pstmt.setInt(4, car.getSeatAmount());
			pstmt.setLong(5, car.getFuelId());
			pstmt.setBoolean(6, car.isAirCondition());
			pstmt.setBoolean(7, car.isAutomaticTransmission());
			pstmt.setInt(8, car.getPrice());
			pstmt.setInt(9, car.getGuaranteeAmount());
			pstmt.executeUpdate();
			ResultSet generatedKeys = pstmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				car.setId(generatedKeys.getLong(1));
			}
			con.commit();
		} catch (SQLException ex) {
			db.rollback(con);
			throw new DBException(Messages.ERR_CANNOT_CREATE_CAR, ex);
		} finally {
			db.close(con, pstmt);
		}
		return car;
	}

	public List<Car> findCars() throws DBException {
		List<Car> carsList = new ArrayList<Car>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			db = DBManager.getInstance();
			con = db.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_CARS);
			while (rs.next()) {
				carsList.add(extractCar(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			db.rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_CARS, ex);
		} finally {
			db.close(con, stmt, rs);
		}
		return carsList;
	}

	public Car findCarById(Long id) throws DBException {
		Car car = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			db = DBManager.getInstance();
			con = db.getConnection();
			pstmt = con.prepareStatement(SQL_FIND_CAR_BY_ID);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				car = extractCar(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			db.rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_CAR_BY_ID, ex);
		} finally {
			db.close(con, pstmt, rs);
		}
		return car;
	}

	public List<Car> findCarsByCategoryId(int categoryId) throws DBException {
		List<Car> carsList = new ArrayList<Car>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			db = DBManager.getInstance();
			con = db.getConnection();
			pstmt = con.prepareStatement(SQL_FIND_CARS_BY_CATEGORY_ID);
			pstmt.setLong(1, categoryId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				carsList.add(extractCar(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			db.rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_CAR_BY_CATEGORY_ID, ex);
		} finally {
			db.close(con, pstmt, rs);
		}
		return carsList;
	}

	public List<Car> findCarsByBrandId(int brandId) throws DBException {
		List<Car> carsList = new ArrayList<Car>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			db = DBManager.getInstance();
			con = db.getConnection();
			pstmt = con.prepareStatement(SQL_FIND_CARS_BY_BRAND_ID);
			pstmt.setLong(1, brandId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				carsList.add(extractCar(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			db.rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_CAR_BY_BRAND_ID, ex);
		} finally {
			db.close(con, pstmt, rs);
		}
		return carsList;
	}

	public void updateCar(Car car) throws DBException {
		Connection con = null;
		try {
			db = DBManager.getInstance();
			con = db.getConnection();
			updateCar(con, car);
			con.commit();
		} catch (SQLException ex) {
			db.rollback(con);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_CAR, ex);
		} finally {
			db.close(con);
		}
	}

	private void updateCar(Connection con, Car car) throws SQLException, DBException {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL_UPDATE_CAR);
			int k = 1;
			pstmt.setLong(k++, car.getFuelId());
			pstmt.setInt(k++, car.getPrice());
			pstmt.setInt(k++, car.getGuaranteeAmount());
			pstmt.setLong(k, car.getId());
			pstmt.executeUpdate();
		} finally {
			db.close(pstmt);
		}
	}

	public void deleteCarById(Long carId) throws DBException {
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			db = DBManager.getInstance();
			con = db.getConnection();
			pstmt = con.prepareStatement(SQL_DELETE_CAR);
			pstmt.setLong(1, carId);
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException ex) {
			db.rollback(con);
			throw new DBException(Messages.ERR_CANNOT_DELETE_CAR_BY_ID, ex);
		} finally {
			db.close(con, pstmt);
		}
	}

	public List<CarBrand> findCarBrands() throws DBException {
		List<CarBrand> brandList = new ArrayList<CarBrand>();
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			db = DBManager.getInstance();
			con = db.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_BRANDS);
			while (rs.next()) {
				brandList.add(extractCarBrand(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			db.rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_CAR_BRANDS, ex);
		} finally {
			db.close(con, stmt, rs);
		}
		return brandList;
	}

	private Car extractCar(ResultSet rs) throws SQLException {
		Car car = new Car();
		car.setId(rs.getLong(Fields.ENTITY_ID));
		car.setBrandId(rs.getInt(Fields.CARS_BRAND_ID));
		car.setModel(rs.getString(Fields.CARS_MODEL));
		car.setCategoryId(rs.getInt(Fields.CARS_CATEGORY_ID));
		car.setSeatAmount(rs.getInt(Fields.CARS_SEAT_AMOUNT));
		car.setFuelId(rs.getInt(Fields.CARS_FUEL_ID));
		car.setAirCondition(rs.getBoolean(Fields.CARS_AIRCONDITION));
		car.setAutomaticTransmission(rs.getBoolean(Fields.CARS_AUTOMATIC_TRNSMISSION));
		car.setPrice(rs.getInt(Fields.CARS_PRICE));
		car.setGuaranteeAmount(rs.getInt(Fields.CARS_GUARANTEE_AMOUNT));
		return car;
	}

	private CarBrand extractCarBrand(ResultSet rs) throws SQLException {
		CarBrand carBrand = new CarBrand();
		carBrand.setId(rs.getLong(Fields.ENTITY_ID));
		carBrand.setName(rs.getString(Fields.BRAND_NAME));
		return carBrand;
	}
}
