package ua.nure.butorin.SummaryTask4.db.entity;

public class Car extends Entity {

	private static final long serialVersionUID = -6080328300800451647L;

	private int brandId;

	private String model;

	private Integer categoryId;

	private Integer seatAmount;

	private Integer fuelId;

	private boolean airCondition;

	private boolean automaticTransmission;

	private Integer price;

	private Integer guaranteeAmount;

	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(int i) {
		this.brandId = i;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getSeatAmount() {
		return seatAmount;
	}

	public void setSeatAmount(Integer seatAmount) {
		this.seatAmount = seatAmount;
	}

	public Integer getFuelId() {
		return fuelId;
	}

	public void setFuelId(Integer fuelId) {
		this.fuelId = fuelId;
	}

	public boolean isAutomaticTransmission() {
		return automaticTransmission;
	}

	public void setAutomaticTransmission(boolean automaticTransmission) {
		this.automaticTransmission = automaticTransmission;
	}

	public boolean isAirCondition() {
		return airCondition;
	}

	public void setAirCondition(boolean airCondition) {
		this.airCondition = airCondition;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getGuaranteeAmount() {
		return guaranteeAmount;
	}

	public void setGuaranteeAmount(Integer guaranteeAmount) {
		this.guaranteeAmount = guaranteeAmount;
	}

	@Override
	public String toString() {
		return "Car [brandId=" + brandId + ", model=" + model + ", categoryId=" + categoryId + ", seatAmount="
				+ seatAmount + ", fuelId=" + fuelId + ", airCondition=" + airCondition + ", automaticTransmission="
				+ automaticTransmission + ", price=" + price + ", guaranteeAmount=" + guaranteeAmount + "]";
	}

}
