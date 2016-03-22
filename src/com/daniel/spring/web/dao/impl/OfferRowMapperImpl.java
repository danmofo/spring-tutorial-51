package com.daniel.spring.web.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.daniel.spring.web.model.Offer;
import com.daniel.spring.web.model.Role;
import com.daniel.spring.web.model.User;

/**
 * RowMapper implementation for extracting results from the Offers database
 * @author danielmoffat
 *
 */
public class OfferRowMapperImpl implements RowMapper<Offer>{

	@Override
	public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
				
		// Map user
		User user = new User();
		user.setAuthority(Role.valueOf(rs.getString("authority")));
		user.setEmail(rs.getString("email"));
		user.setEnabled(rs.getBoolean("enabled"));
		user.setUsername(rs.getString("username"));
		user.setName(rs.getString("name"));
		
		// Map offer
		Offer offer = new Offer();
		offer.setId(rs.getInt("id"));
		offer.setText(rs.getString("text"));
		offer.setUser(user);
		
		return offer;
	}

}
