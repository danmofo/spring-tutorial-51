package com.daniel.spring.web.dao;

import java.util.List;

import com.daniel.spring.web.model.Offer;

public interface OfferDao {
	
	// Create
	boolean add(Offer offer);
	int[] addAll(List<Offer> offers);
	
	// Read
	Offer retrieve(int id);
	Offer retrieve(String name);
	List<Offer> list(int limit);
	List<Offer> list();
	
	// Update
	boolean update(Offer offer);
	
	// Delete
	boolean delete();
	
	// Testing
	Offer retrieveUnsafe(int id);
}