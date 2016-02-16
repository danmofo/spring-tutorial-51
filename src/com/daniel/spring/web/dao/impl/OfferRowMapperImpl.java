package com.daniel.spring.web.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.daniel.spring.web.model.Offer;

/**
 * RowMapper implementation for extracting results from the Offers database
 * @author danielmoffat
 *
 */
public class OfferRowMapperImpl implements RowMapper<Offer>{

	@Override
	public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
		Offer offer = new Offer();
		offer.setId(rs.getInt("id"));
		offer.setName(rs.getString("name"));
		offer.setText(rs.getString("text"));
		offer.setEmail(rs.getString("email"));
		return offer;
	}

}
