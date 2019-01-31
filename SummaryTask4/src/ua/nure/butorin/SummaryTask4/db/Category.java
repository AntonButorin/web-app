package ua.nure.butorin.SummaryTask4.db;

import ua.nure.butorin.SummaryTask4.db.entity.Car;

public enum Category {
	SMALL_CAR, MEDIUM_CAR, LARGE_CAR, ESTATE_CAR, PREMIUM_CAR, PEOPLE_CARRIERS, SUVS;
	
	public static Category getCategory(Car car) {
		int categoryId = car.getCategoryId();
		return Category.values()[categoryId];
	}
	
	public String getName() {
		return this.name().toLowerCase();
	}

}
