package ua.nure.butorin.SummaryTask4.db;

import ua.nure.butorin.SummaryTask4.db.entity.User;

public enum Role {
	ADMIN, MANAGER, CLIENT;

	public static Role getRole(User user) {
		int roleId = user.getRoleId();
		return Role.values()[roleId];
	}

	public String getName() {
		return this.name().toLowerCase();
	}
}
