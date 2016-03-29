package com.daniel.spring.web.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.daniel.spring.web.dao.CrudDao;
import com.daniel.spring.web.model.Role;
import com.daniel.spring.web.model.User;

@Service("userService")
public class UserService {

	private static final Logger logger = LogManager.getLogger(UserService.class);
	
	private CrudDao<User, String> dao;
	
	@Autowired(required=true)
	public void setUserDao(CrudDao<User, String> dao) {
		this.dao = dao;
	}
	
	@Secured("ROLE_ADMIN")
	public List<User> getAll() {
		logger.info("Getting all.");
		return dao.list(10);
	}
	
	public boolean add(User user) {
		logger.info("Adding user " + user);
		return dao.add(user);
	}
	
	public boolean addAdmin(User user) {
		logger.info("Adding an admin.." + user);
		user.setAuthority(Role.ROLE_ADMIN);
		return dao.add(user);
	}
	
	public boolean update(User user) {
		logger.info("Updating user " + user);
		return dao.update(user);
	}
	
	public User getById(String username) {
		logger.info("Getting user by ID: " + username);
		return dao.retrieve(username);
	}
	
	public boolean deactivate(User user) {
		logger.info("Deactivating " + user);
		return dao.update(user);
	}
}
