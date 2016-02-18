package com.daniel.spring.web.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.daniel.spring.web.dao.CrudDao;
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
		params2.addValue("authority", user.getAuthority().getStringRepresentation());
		params2.addValue("username", user.getUsername());
		
		// These will throw runtime exceptions if something goes wrong regardless
		jdbc.update("insert into users (username, password, email) values (:username, :password, :email)", params);
		jdbc.update("insert into authorities (username, authority) values (:username, :authority)", params2);
		
	}

	@Override
	public User retrieve(String username) {
		MapSqlParameterSource params = new MapSqlParameterSource("username", username);
		
		List<User> user = this.jdbc.query("select * from users where username = :username", params, new UserRowMapperImpl());
		
		return user.size() != 0 ? user.get(0) : null;
		
	}

	@Override
	public List<User> list(int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> list() {
		return null;
	}

	@Override
	public boolean update(User user) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(user);
		
		return this.jdbc.update("update users set username=:username, password=:password, enabled=:enabled where id=:id", params) == 1;
	}

	@Override
	public boolean delete() {
		// TODO Auto-generated method stub
		return false;
	}

}
