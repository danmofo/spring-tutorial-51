package com.daniel.spring.web.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.daniel.spring.web.dao.CrudDao;
import com.daniel.spring.web.dao.mapper.UserRowMapperImpl;
import com.daniel.spring.web.model.User;

@Repository
public class JdbcUserDaoImpl implements CrudDao<User, String>{
	
	public static final String QUERY_INSERT 				= "insert into users (username, authority, name, password, email) values (:username, :authority, :name, :password, :email)";
	public static final String QUERY_RETRIEVE_ALL 			= "select * from users ";
	public static final String QUERY_RETRIEVE_ALL_LIMIT 	= QUERY_RETRIEVE_ALL + "limit :limit";
	public static final String QUERY_RETRIEVE_SINGLE 		= QUERY_RETRIEVE_ALL + "where username = :username";
	public static final String QUERY_UPDATE 				= "update users set authority=:authority, name=:name, password=:password, enabled=:enabled, email=:email where username = :username";
	public static final String QUERY_DELETE 				= "delete from users where username = :username";
	
	private NamedParameterJdbcTemplate jdbc;
	private PasswordEncoder passwordEncoder;
	
	@Autowired(required=true)
	public void setDataSource(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	@Transactional
	@Override
	public boolean add(User user) {
		MapSqlParameterSource userParams = new MapSqlParameterSource();
		userParams.addValue("username", user.getUsername());
		userParams.addValue("password", passwordEncoder.encode(user.getPassword()));
		userParams.addValue("email", user.getEmail());
		userParams.addValue("name", user.getName());
		userParams.addValue("authority", user.getAuthority().toString());		
		
		return jdbc.update(QUERY_INSERT, userParams) == 1;
	}

	@Override
	public User retrieve(String username) {
		MapSqlParameterSource params = new MapSqlParameterSource("username", username);
		List<User> user = this.jdbc.query(QUERY_RETRIEVE_SINGLE, params, new UserRowMapperImpl());
		
		return user.size() != 0 ? user.get(0) : null;
	}

	@Override
	public List<User> list(int limit) {
		MapSqlParameterSource params = new MapSqlParameterSource("limit", limit);
		return this.jdbc.query(QUERY_RETRIEVE_ALL_LIMIT, params, new UserRowMapperImpl());
	}

	@Override
	public List<User> list() {
		return this.jdbc.query(QUERY_RETRIEVE_ALL, new UserRowMapperImpl());		
	}

	@Override
	public boolean update(User user) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("username", user.getUsername());
		params.addValue("password", passwordEncoder.encode(user.getPassword()));
		params.addValue("email", user.getEmail());
		params.addValue("enabled", user.isEnabled());
		params.addValue("name", user.getName());
		params.addValue("authority", user.getAuthority().toString());
				
		return this.jdbc.update(QUERY_UPDATE, params) == 1;
	}

	@Override
	public boolean delete(User user) {
		user.setEnabled(false);
		return update(user);
	}

	@Override
	public int getLastInsertId() {
		// TODO Auto-generated method stub
		return 0;
	}

}
