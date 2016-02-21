package com.daniel.spring.web.model;

public enum Role {
	ROLE_USER,
	ROLE_ADMIN;
	
	public static void main(String[] args) {
	
		System.out.println(Role.ROLE_USER.toString());
		System.out.println(Role.valueOf("ROLE_USER"));
		
	}
}
