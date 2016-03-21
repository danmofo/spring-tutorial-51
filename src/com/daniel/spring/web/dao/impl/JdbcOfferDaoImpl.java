package com.daniel.spring.web.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.daniel.spring.web.dao.CrudDao;
import com.daniel.spring.web.model.Offer;

@Repository("offerDao")
public class JdbcOfferDaoImpl implements CrudDao<Offer, Integer> {
	
	// Probably much better ways to do this, but inline SQL hurts my eyes.
	public static final String QUERY_INSERT 				= "insert into offer (text, username) values (:text, :username)";
	public static final String QUERY_RETRIEVE_ALL 			= "select * from offer inner join users on offer.username = users.username ";
	public static final String QUERY_RETRIEVE_ALL_LIMIT 	= QUERY_RETRIEVE_ALL + "limit :limit";
	public static final String QUERY_RETRIEVE_SINGLE 		= QUERY_RETRIEVE_ALL + "where offer.id = :id";
	public static final String QUERY_UPDATE 				= "update offer set text= :text where id = :id";
	public static final String QUERY_DELETE 				= "delete from offer where id = :id";
	
	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired(required=true)
	public void setDataSource(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	@Transactional
	@Override
	public boolean add(Offer offer) {
		System.out.println(offer);
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("text", offer.getText());
		params.addValue("username", offer.getUser().getUsername());
			
		return jdbc.update(QUERY_INSERT, params) == 1;
	}
	

	@Override
	public Offer retrieve(Integer id) {
		MapSqlParameterSource params = new MapSqlParameterSource("id", id);
		
		List<Offer> offers = jdbc.query(QUERY_RETRIEVE_SINGLE, params, new OfferRowMapperImpl());
		
		System.out.println(offers);
		
		return offers.size() == 0 ? null : offers.get(0);
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
		return jdbc.update(QUERY_UPDATE,
				new BeanPropertySqlParameterSource(offer)) == 1;
	}

	@Override
	public boolean delete(Offer offer) {
		MapSqlParameterSource params = new MapSqlParameterSource("id", offer.getId());
		return jdbc.update(QUERY_DELETE, params) == 1;
	}

}
