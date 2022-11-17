package com.adnan.enums;

public enum Roles {

	ADMIN("ROLE_ADMIN"),
	USER("ROLE_USER");
	
	private String userRole;

	Roles(String userRole) {
		this.userRole = userRole;
	}

	public String getRole() {
		return this.userRole;
	}
	
}
