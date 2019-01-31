package ua.nure.butorin.SummaryTask4.db;

import ua.nure.butorin.SummaryTask4.db.entity.Order;

/**
 * Status of orders.
 * 
 * @author A.Butorin
 * 
 */
public enum Status {
	OPENED, CONFIRMED, CANCELED, PAID, COMPLAINED, CLOSED;
	
	public static Status getStatus(Order order) {
		int statusId = order.getStatusId();
		return Status.values()[statusId];
	}
	
	public String getName() {
		return this.name().toLowerCase();
	}
}