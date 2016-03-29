package com.daniel.spring.web.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.daniel.spring.web.dao.HibernateCrudDao;
import com.daniel.spring.web.model.Offer;

/**
 * Offer DAO using Hibernate.
 * 
 * @author dan
 * 
**/
@Repository("offerDao")
@Transactional
public class HibernateOfferDaoImpl implements HibernateCrudDao<Offer, Integer> {
	
	private static final Logger logger = LogManager.getLogger(HibernateOfferDaoImpl.class);
	
	@Autowired(required=true)
	private SessionFactory sessionFactory;
	
	public Session session() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public Integer add(Offer model) {
		return 1;
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
	public void update(Offer model) {

	}

	@Override
	public void delete(Offer model) {

	}

	@Override
	public List<Offer> orderBy(Order order) {
		// TODO Auto-generated method stub
		return null;
	}
}
