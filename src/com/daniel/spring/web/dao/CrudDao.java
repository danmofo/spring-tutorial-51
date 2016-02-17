package com.daniel.spring.web.dao;

import java.util.List;

/**
 * Common interface for basic CRUD operations
 * @author danielmoffat
 *
 * @param <T> The model
 */

public interface CrudDao<T, K> {
	
	// Create
	boolean add(T model);
	int[] addAll(List<T> models);
	
	// Read
	T retrieve(K key);
	List<T> list(int limit);
	List<T> list();
	
	// Update
	boolean update(T model);
	
	// Delete
	boolean delete();	
}
