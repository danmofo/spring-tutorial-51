package com.daniel.spring.web.dao;

import java.util.List;

/**
 * Common interface for basic CRUD operations on the specified model.
 * @author danielmoffat
 *
 * @param <T> The model
 * @param <K> The primary key type
 */

public interface CrudDao<T, K> {
		
	boolean add(T model);
	
	// Read
	T retrieve(K key);
	List<T> list(int limit);
	List<T> list();
	
	// Update
	boolean update(T model);
	
	// Delete
	boolean delete(T model);
	
	// Ideally this would be returned from 'add' directly as this
	// is not thread safe. If two offers are created at the same time
	// one user could get redirected to the wrong page!
	int getLastInsertId();
	
}
