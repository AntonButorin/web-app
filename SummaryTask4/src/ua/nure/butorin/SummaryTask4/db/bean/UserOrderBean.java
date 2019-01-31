package ua.nure.butorin.SummaryTask4.db.bean;

import ua.nure.butorin.SummaryTask4.db.entity.Entity;

/**
 * Provide records for virtual table:
 * 
 * 
 */
public class UserOrderBean extends Entity {

	private static final long serialVersionUID = -1183943269766799041L;

	private Long orderId;

	private String userFirstName;

	private String userLastName;

	private int carBrandId;

	private String carModel;

	private int price;

	private int rentPeriod;

	private boolean driver;

	private int orderBill;

	private String orderStatusName;
	
	private String orderComments;

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getRentPeriod() {
		return rentPeriod;
	}

	public void setRentPeriod(int rentPeriod) {
		this.rentPeriod = rentPeriod;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public int getCarBrandId() {
		return carBrandId;
	}

	public void setCarBrandId(int carBrandId) {
		this.carBrandId = carBrandId;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public boolean isDriver() {
		return driver;
	}

	public void setDriver(boolean driver) {
		this.driver = driver;
	}

	public int getOrderBill() {
		return orderBill;
	}

	public void setOrderBill(int orderBill) {
		this.orderBill = orderBill;
	}

	public String getOrderStatusName() {
		return orderStatusName;
	}

	public void setOrderStatusName(String orderStatusName) {
		this.orderStatusName = orderStatusName;
	}

	public String getOrderComments() {
		return orderComments;
	}

	public void setOrderComments(String orderComments) {
		this.orderComments = orderComments;
	}

	@Override
	public String toString() {
		return "UserOrderBean [orderId=" + orderId + ", userFirstName=" + userFirstName + ", userLastName="
				+ userLastName + ", carBrandId=" + carBrandId + ", carModel=" + carModel + ", price=" + price
				+ ", rentPeriod=" + rentPeriod + ", driver=" + driver + ", orderBill=" + orderBill
				+ ", orderStatusName=" + orderStatusName + ", orderComments=" + orderComments + "]";
	}
}
