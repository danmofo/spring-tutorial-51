package com.daniel.spring.web.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Convenience class for database access using Hibernate.
 * 
 * Implements all basic operations defined in HibernateCrudDao<T, K> as well
 * as providing a handy session method to get the current session.
 * 
 * e.g. An implementing class could look like:
 * 
 * public class HibernateUserDaoImpl extends HibernateDao<User, String> {
 * 
 *   // Extra method only available with this model object
 *   public List<User> listWithLimitAndOrder(int limit, Order order) {
 *     List<User> results = session().createCriteria(User.class)
 *     									.setMaxResults(limit)
 *     									.addOrder(order)
 *										.list();
 *
 *		return results;
 *   }
 * }
 * 
 * 
 * todo: Implement methods
 * 
 * @author danielmoffat
 *
 */
public class HibernateDao<T, K> implements HibernateCrudDao<T, K>{

	@Autowired(required=true)
	private SessionFactory sessionFactory;
	
	private Session session() {
		return sessionFactory.getCurrentSession();
	}
		
	@Override
	public K add(T model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T retrieve(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> list(int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> orderBy(Order order) {
		return null;
	}

	@Override
	public void update(T model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(T model) {
		// TODO Auto-generated method stub
		
	}

}
