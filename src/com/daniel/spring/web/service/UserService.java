package com.daniel.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.spring.web.dao.impl.JdbcUserDaoImpl;
import com.daniel.spring.web.model.Role;
import com.daniel.spring.web.model.User;

@Service("userService")
public class UserService {

	private JdbcUserDaoImpl dao;
	
	@Autowired(required=true)
	public void setUserDao(JdbcUserDaoImpl dao) {
		this.dao = dao;
	}
	
	public List<User> getAll() {
		return dao.list();
	}
	
	public void add(User user) {
		dao.add(user);
	}
	
	public void addAdmin(User user) {
		user.setAuthority(Role.ROLE_ADMIN);
		dao.add(user);
	}
	
	public boolean update(User user) {
		return dao.update(user);
	}
	
	public User getById(String username) {
		return dao.retrieve(username);
	}
}
