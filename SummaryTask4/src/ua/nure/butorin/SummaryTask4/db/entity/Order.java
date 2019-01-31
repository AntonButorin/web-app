package ua.nure.butorin.SummaryTask4.db.entity;

public class Order extends Entity {

	private static final long serialVersionUID = 5692708766041889396L;

	private Long bill;

	private Long userId;

	private Long carId;

	private String passport;

	private boolean driver;

	private int price;

	private int period;

	private int amount;

	private int statusId;

	private int compensationSum;

	private String comments;

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public long getBill() {
		return bill;
	}

	public void setBill(Long bill) {
		this.bill = bill;
	}

	public long getCarId() {
		return carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public boolean isDriver() {
		return driver;
	}

	public void setDriver(boolean driver) {
		this.driver = driver;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int getCompensationSum() {
		return compensationSum;
	}

	public void setCompensationSum(int compensationSum) {
		this.compensationSum = compensationSum;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Order [bill=" + bill + ", userId=" + userId + ", carId=" + carId + ", passport=" + passport
				+ ", driver=" + driver + ", price=" + price + ", period=" + period + ", amount=" + amount
				+ ", statusId=" + statusId + "]";
	}
}
