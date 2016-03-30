package com.daniel.spring.web.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.daniel.spring.web.dao.HibernateCrudDao;
import com.daniel.spring.web.model.User;


/**
 * User DAO using Hibernate.
 * 
 * todo: check book @ work for correct method syntax, there is a mix of hql and using
 * createCriteria directly
 * todo: catch HibernateException to verify success of operations
 * 
 * @author dan
 *
 */
@Repository("userDao")
@Transactional
public class HibernateUserDaoImpl implements HibernateCrudDao<User, String> {

	private static final Logger logger = LogManager.getLogger(HibernateOfferDaoImpl.class);
		
	@Autowired(required=true)
	private SessionFactory sessionFactory;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private Session session() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public String add(User model) {
		model.setPassword(passwordEncoder.encode(model.getPassword()));
		logger.info("HibernateUserDao add(): {}", model);
		
		// todo: add condition for when this doesn't work as intended
		return (String) session().save(model);
	}

	@Override
	public User retrieve(String key) {
		return session().get(User.class, key);
	}

	// docs: https://docs.jboss.org/hibernate/orm/5.0/userGuide/en-US/html_single/#d5e1774
	@SuppressWarnings("unchecked")
	@Override
	public List<User> list(int limit) {
		logger.info("limit {}", limit);
		List<User> results = session()
								.createCriteria(User.class)
								.setMaxResults(limit)
								.list();
		logger.info("HibernateUserDao list(int limit): {}", results);
		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> list() {
		List<User> results = session().createQuery("from User").list();
		
		logger.info("HibernateUserDao list() {}", results);
		
		return results;
	}

	@Override
	public void update(User model) {
		logger.info("HibernateUserDao update():");
		session().update(model);
		logger.info("Updated username \"{}\"");
	}

	@Override
	public void delete(User model) {
		logger.info("HibernateUserDao delete()");
		session().delete(model);
		logger.info("Deleted username \"{}\" from the database!", model.getUsername());
	}



	@SuppressWarnings("unchecked")
	@Override
	public List<User> orderBy(Order order) {
		List<User> results = session()
				.createCriteria(User.class)
				.addOrder(order)
				.list();
		
		logger.info("HibernateUserDao sortBy(Order order): {}", results);
		return results;
	}

}
