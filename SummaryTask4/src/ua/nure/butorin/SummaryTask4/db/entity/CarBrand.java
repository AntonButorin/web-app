package ua.nure.butorin.SummaryTask4.db.entity;

public class CarBrand extends Entity {

	private static final long serialVersionUID = -7532306265816083549L;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "CarBrand [name=" + name + "]";
	}
}
