package com.daniel.spring.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daniel.spring.web.dao.CrudDao;
import com.daniel.spring.web.dao.impl.JdbcUserDaoImpl;
import com.daniel.spring.web.model.User;

@Component("userService")
public class UserService {

	private JdbcUserDaoImpl dao;
	
	@Autowired(required=true)
	public void setUserDao(JdbcUserDaoImpl dao) {
		this.dao = dao;
	}
	
	public boolean add(User user) {
		return dao.add(user);
	}
	
	public boolean update(User user) {
		return dao.update(user);
	}
	
	public User getById(String username) {
		return dao.retrieve(username);
	}
}
