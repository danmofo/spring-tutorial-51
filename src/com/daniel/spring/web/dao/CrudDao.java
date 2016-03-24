package com.daniel.spring.web.dao;

import java.util.List;

/**
 * Common interface for basic CRUD operations
 * @author danielmoffat
 *
 * @param <T> The model
 * @param <K> The primary key type
 */

public interface CrudDao<T, K> {
	
	// Create
	boolean add(T model);
	
	// Read
	T retrieve(K key);
	List<T> list(int limit);
	List<T> list();
	
	// Update
	boolean update(T model);
	
	// Delete
	boolean delete(T model);	
}
