package com.daniel.spring.web.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.daniel.spring.web.model.Role;
import com.daniel.spring.web.model.User;

public class UserRowMapperImpl implements RowMapper<User>{

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setUsername(rs.getString("username"));
		user.setAuthority(Role.valueOf(rs.getString("authority")));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));
		user.setEnabled(rs.getBoolean("enabled"));
		user.setEmail(rs.getString("email"));
		
		return user;
	}

}
 