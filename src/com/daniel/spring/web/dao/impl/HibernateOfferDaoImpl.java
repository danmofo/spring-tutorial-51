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
	
	private Session session() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public Integer add(Offer model) {
		logger.info("HibernateOfferDao add(): {}", model);

		return (Integer) session().save(model);
	}

	@Override
	public Offer retrieve(Integer key) {
		return session().get(Offer.class, key);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Offer> list(int limit) {
		logger.info("limit {}", limit);
		List<Offer> results = session()
								.createCriteria(Offer.class)
								.setMaxResults(limit)
								.list();
		logger.info("HibernateOfferDao list(int limit): {}", results);
		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Offer> list() {
		List<Offer> results = session().createQuery("from Offer").list();
		
		logger.info("HibernateOfferDao list() {}", results);
		
		return results;
	}

	@Override
	public void update(Offer model) {
		logger.info("HibernateOfferDao update():");
		session().update(model);
		logger.info("Updated offer \"{}\"");
	}

	@Override
	public void delete(Offer model) {
		logger.info("HibernateOfferDao delete()");
		session().delete(model);
		logger.info("Deleted ID \"{}\" from the database!", model.getId());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Offer> orderBy(Order order) {
		List<Offer> results = session()
				.createCriteria(Offer.class)
				.addOrder(order)
				.list();
		
		logger.info("HibernateOfferDao sortBy(Order order): {}", results);
		return results;
	}
}
