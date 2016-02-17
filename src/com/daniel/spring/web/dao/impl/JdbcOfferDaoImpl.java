package com.daniel.spring.web.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.daniel.spring.web.dao.OfferDao;
import com.daniel.spring.web.model.Offer;

@Component("offerDao")
public class JdbcOfferDaoImpl implements OfferDao {
	
	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired(required=true)
	public void setDataSource(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	@Transactional
	@Override
	public boolean add(Offer offer) {
		return jdbc.update("insert into offer (id, name, email, text) values (:id, :name, :email, :text)",
				new BeanPropertySqlParameterSource(offer)) == 1;
	}
	
	@Transactional
	@Override
	public int[] addAll(List<Offer> offers) {
		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(offers.toArray());
		
		return jdbc.batchUpdate("insert into offer (id, name, email, text) values (:id, :name, :email, :text)", params);
	}

	@Override
	public Offer retrieve(int id) {
		MapSqlParameterSource params = new MapSqlParameterSource("id", id);
		
		// queryForObject expects exactly one result, so we have to use query instead..
		// see: {@link http://stackoverflow.com/questions/10606229/jdbctemplate-query-for-string-emptyresultdataaccessexception-incorrect-result}
		List<Offer> offers = jdbc.query("select * from offer where id = :id", params, new OfferRowMapperImpl());
		
		System.out.println(offers);
		
		return offers.size() == 0 ? null : offers.get(0);
	}
	

	@Override
	public boolean delete() {
		return false;
	}

	@Override
	public List<Offer> list(int limit) {
		MapSqlParameterSource params = new MapSqlParameterSource("limit", limit);
		
		return jdbc.query("select * from offer limit :limit", params, new OfferRowMapperImpl());
	}

	@Override
	public List<Offer> list() {
		return jdbc.query("select * from offer", new OfferRowMapperImpl());
	}

	@Override
	public boolean update(Offer offer) {
		return jdbc.update("update offer set name=:name, email=:email, text=:text where id=:id",
				new BeanPropertySqlParameterSource(offer)) == 1;
	}

	@Override
	public Offer retrieve(String name) {
		MapSqlParameterSource params = new MapSqlParameterSource("name", name);
		
		List<Offer> offers = jdbc.query("select * from offer where name = :name", params, new OfferRowMapperImpl());
				
		return offers.size() == 0 ? null : offers.get(0);
	}

	public Offer retrieveUnsafe(int id) {
		MapSqlParameterSource params = new MapSqlParameterSource("id", id);
		
		return jdbc.queryForObject("select * from offer where id = :id", params, new OfferRowMapperImpl());
	}

}
