package com.daniel.spring.web.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {
	
	@Size(min=1, message="Username must be at least {min} characters long.")
	private String username;
	
	@Size(min=3, max=20, message="Password must be between {min} and {max} characters long.")
	private String password;
	
	private boolean enabled = true;

	public String getUsername() {
		return username;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [username=");
		builder.append(username);
		builder.append(", password=");
		builder.append(password);
		builder.append(", enabled=");
		builder.append(enabled);
		builder.append("]");
		return builder.toString();
	}
}
