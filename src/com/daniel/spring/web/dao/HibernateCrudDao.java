package com.daniel.spring.web.dao;

import java.util.List;

import org.hibernate.criterion.Order;

/**
 * Modified CrudDao interface for DAOs since Hibernate returns different
 * types to JdbcTemplate and I don't want to break those (or rewrite them to return said types).
 * 
 * We can also remove the ugly getLastInsertId method.
 * 
 * @author dan
 *
 */
public interface HibernateCrudDao<T, K> {
	K add(T model);
	T retrieve(K key);
	List<T> list(int limit);
	List<T> list();
	List<T> orderBy(Order order);
	void update(T model);
	void delete(T model);
}
