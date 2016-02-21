package com.daniel.spring.web.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

public class User {
	
	@Size(min=1)
	private String username;
	
	@Size(min=3, max=20, message="Password must be between {min} and {max} characters long.")
	private String password;
	
	@Email
	private String email;
		
	private Role authority = Role.ROLE_USER;
	
	private boolean enabled = true;
	
	public String getUsername() {
		return username;
	}
		
	public void setAuthority(Role role) {
		this.authority = role;
	}
	
	public Role getAuthority() {
		return authority;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [username=");
		builder.append(username);
		builder.append(", password=");
		builder.append(password);
		builder.append(", email=");
		builder.append(email);
		builder.append(", authority=");
		builder.append(authority);
		builder.append(", enabled=");
		builder.append(enabled);
		builder.append("]");
		return builder.toString();
	}

	public static void main(String[] args) {
		
		System.out.println(new User());
		
	}
}
