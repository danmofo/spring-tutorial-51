package com.daniel.spring.web.model;

public enum Role {
	USER("ROLE_USER"),
	ADMIN("ROLE_ADMIN");
	
	private String stringRepresentation;
	
	Role(String stringRepresentation) {
		this.stringRepresentation = stringRepresentation;
	}
	
	public String getStringRepresentation() {
		return stringRepresentation;
	}
}
