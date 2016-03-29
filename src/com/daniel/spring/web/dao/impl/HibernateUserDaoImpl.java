package com.daniel.spring.web.dao.impl;

import java.util.List;

import com.daniel.spring.web.dao.CrudDao;
import com.daniel.spring.web.model.User;

public class HibernateUserDaoImpl implements CrudDao<User, String> {

	@Override
	public boolean add(User model) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User retrieve(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> list(int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(User model) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(User model) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getLastInsertId() {
		// TODO Auto-generated method stub
		return 0;
	}

}
