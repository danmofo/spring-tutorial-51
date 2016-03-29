package com.daniel.spring.web.service;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daniel.spring.web.dao.impl.JdbcOfferDaoImpl;
import com.daniel.spring.web.model.Offer;

@Service("offerService")
@Transactional
public class OfferService {

	private static final Logger logger = LogManager.getLogger(OfferService.class);
	
	private JdbcOfferDaoImpl dao;
	
	@Autowired(required=true)
	public void setOfferDao(JdbcOfferDaoImpl dao) {
		this.dao = dao;
	}
	
	public boolean add(Offer offer) {
		logger.info("Adding an offer " + offer);
		return dao.add(offer);
	}
	
	public boolean update(Offer offer) {
		logger.info("Updating an offer " + offer);
		return dao.update(offer);
	}
	
	public Offer getById(int id) {
		logger.info("Getting by ID: " + id);
		return dao.retrieve(id);
	}
	
	public List<Offer> getByUsername(String username) {
		logger.info("Getting by username: " + username);
		return dao.retrieve(username);
	}
	
	public List<Offer> getN(int limit) {
		logger.info("Getting N: " + limit);
		return dao.list(limit);
	}
	
	public List<Offer> getAll() {
		logger.info("Getting all..");
		return dao.list();
	}
	
	public List<Offer> getCurrent() {
		logger.info("Getting current..");
		return dao.list(5);
	}
	
	public int getLastAddedId() {
		logger.info("Getting last insert ID");
		return dao.getLastInsertId();
	}
}
