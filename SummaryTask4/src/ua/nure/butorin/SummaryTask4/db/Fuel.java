package ua.nure.butorin.SummaryTask4.db;

import ua.nure.butorin.SummaryTask4.db.entity.Car;

public enum Fuel {
	
PATROL, DIESEL, GAS, HYBRYD, ELECTRO;
	
	public static Fuel getFuel(Car car) {
		int fuelId = car.getFuelId();
		return Fuel.values()[fuelId];
	}
	
	public String getName() {
		return this.name().toLowerCase();
	}

}
