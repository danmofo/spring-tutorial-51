package com.daniel.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daniel.spring.web.dao.impl.JdbcOfferDaoImpl;
import com.daniel.spring.web.model.Offer;

@Service("offerService")
@Transactional
public class OfferService {

	private JdbcOfferDaoImpl dao;
	
	@Autowired(required=true)
	public void setOfferDao(JdbcOfferDaoImpl dao) {
		this.dao = dao;
	}
	
	public boolean add(Offer offer) {
		return dao.add(offer);
	}
	
	public boolean update(Offer offer) {
		return dao.update(offer);
	}
	
	public Offer getById(int id) {
		return dao.retrieve(id);
	}
	
	public List<Offer> getByUsername(String username) {
		return dao.retrieve(username);
	}
	
	public List<Offer> getN(int limit) {
		return dao.list(limit);
	}
	
	public List<Offer> getAll() {
		return dao.list();
	}
	
	public List<Offer> getCurrent() {
		return dao.list(5);
	}
	
	public int getLastAddedId() {
		return dao.getLastInsertId();
	}
}
