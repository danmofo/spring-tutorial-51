package com.daniel.spring.web.dao.impl;

import java.util.List;

import com.daniel.spring.web.dao.CrudDao;
import com.daniel.spring.web.model.Offer;

public class HibernateOfferDaoImpl implements CrudDao<Offer, Integer> {

	@Override
	public boolean add(Offer model) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Offer retrieve(Integer key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Offer> list(int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Offer> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Offer model) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Offer model) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getLastInsertId() {
		// TODO Auto-generated method stub
		return 0;
	}

}
