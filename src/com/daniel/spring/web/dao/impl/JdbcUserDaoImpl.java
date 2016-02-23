package com.daniel.spring.web.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.daniel.spring.web.dao.CrudDao;
import com.daniel.spring.web.model.Role;
import com.daniel.spring.web.model.User;

@Component("userDao")
public class JdbcUserDaoImpl implements CrudDao<User, String>{
	
	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	@Transactional
	@Override
	public void add(User user) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(user);
		MapSqlParameterSource params2 = new MapSqlParameterSource();
		params2.addValue("authority", user.getAuthority().toString());
		params2.addValue("username", user.getUsername());
		
		// These will throw runtime exceptions if something goes wrong regardless
		jdbc.update("insert into users (usersname, password, email) values (:username, :password, :email)", params);
		jdbc.update("insert into authorities (username, authority) values (:username, :authority)", params2);
		
	}

	@Override
	public User retrieve(String username) {
		MapSqlParameterSource params = new MapSqlParameterSource("username", username);
				
		List<User> user = this.jdbc.query("select * from users where username = :username", params, new UserRowMapperImpl());
		
		return user.size() != 0 ? user.get(0) : null;
		
	}

	// Using shortened BeanPropertyRowMapper.newInstance
	@Override
	public List<User> list(int limit) {
		System.out.println("Using list(int limit)");
		return this.jdbc.query("select u.username, u.enabled, u.email, a.authority from users u inner join authorities a on a.username = u.username", BeanPropertyRowMapper.newInstance(User.class));
	}

	// Equivalent of the above, manually mapping rows
	@Override
	public List<User> list() {
		List<User> users = this.jdbc.query("select u.username, u.enabled, u.email, a.authority from users u inner join authorities a on a.username = u.username order by u.enabled desc", new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setUsername(rs.getString("username"));
				user.setAuthority(Role.valueOf(rs.getString("authority")));
				user.setEmail(rs.getString("email"));
				user.setEnabled(rs.getBoolean("enabled"));
				
				return user;
			}
		});
		
		return users;
		
	}

	@Override
	public boolean update(User user) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(user);
		
		return this.jdbc.update("update users set username=:username, password=:password, enabled=:enabled where username=:username", params) == 1;
	}

	@Override
	public boolean delete(User user) {
		return update(user);
	}

}
