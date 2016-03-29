package com.daniel.spring.web.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.daniel.spring.web.dao.CrudDao;
import com.daniel.spring.web.dao.mapper.OfferRowMapperImpl;
import com.daniel.spring.web.model.Offer;

@Repository("offerDao")
public class JdbcOfferDaoImpl implements CrudDao<Offer, Integer> {
	
	// Probably much better ways to do this, but inline SQL hurts my eyes.
	public static final String QUERY_INSERT 				= "insert into offer (text, username) values (:text, :username)";
	public static final String QUERY_RETRIEVE_ALL 			= "select * from offer inner join users on offer.username = users.username ";
	public static final String QUERY_RETRIEVE_BY_USER		= QUERY_RETRIEVE_ALL + "";
	public static final String QUERY_RETRIEVE_ALL_LIMIT 	= QUERY_RETRIEVE_ALL + "limit :limit";
	public static final String QUERY_RETRIEVE_SINGLE 		= QUERY_RETRIEVE_ALL + "where offer.id = :id";
	public static final String QUERY_UPDATE 				= "update offer set text = :text where id = :id";
	public static final String QUERY_DELETE 				= "delete from offer where id = :id";
	public static final String QUERY_LAST_INSERT_ID			= "select last_insert_id()";
	
	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired(required=true)
	public void setDataSource(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	@Transactional
	@Override
	public boolean add(Offer offer) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("text", offer.getText());
		params.addValue("username", offer.getUser().getUsername());	
		return jdbc.update(QUERY_INSERT, params) == 1;
	}
	
	@Override
	public Offer retrieve(Integer id) {
		MapSqlParameterSource params = new MapSqlParameterSource("id", id);
		
		List<Offer> offers = jdbc.query(QUERY_RETRIEVE_SINGLE, params, new OfferRowMapperImpl());
				
		return offers.size() == 0 ? null : offers.get(0);
	}
	
	public List<Offer> retrieve(String username) {
		return null;
	}

	@Override
	public List<Offer> list(int limit) {
		MapSqlParameterSource params = new MapSqlParameterSource("limit", limit);
		
		return jdbc.query(QUERY_RETRIEVE_ALL_LIMIT, params, new OfferRowMapperImpl());
	}

	@Override
	public List<Offer> list() {
		return jdbc.query(QUERY_RETRIEVE_ALL, new OfferRowMapperImpl());
	}

	@Override
	public boolean update(Offer offer) {
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		params.addValue("id", offer.getId());
		params.addValue("text", offer.getText());
				
		return jdbc.update(QUERY_UPDATE, params) == 1;
	}

	@Override
	public boolean delete(Offer offer) {
		
		MapSqlParameterSource params = new MapSqlParameterSource("id", offer.getId());		
			
		return jdbc.update(QUERY_DELETE, params) == 1;
	}

	@Override
	public int getLastInsertId() {
		return jdbc.queryForObject(QUERY_LAST_INSERT_ID, new MapSqlParameterSource(), Integer.class);
	}

}
