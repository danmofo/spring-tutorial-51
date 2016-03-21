package com.daniel.spring.web.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.daniel.spring.web.model.Role;
import com.daniel.spring.web.model.User;

public class UserRowMapperImpl implements RowMapper<User>{

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setName(rs.getString("name"));
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setEnabled(rs.getBoolean("enabled"));
		user.setEmail(rs.getString("email"));
		user.setAuthority(Role.valueOf(rs.getString("authority")));
		return user;
	}

}
 