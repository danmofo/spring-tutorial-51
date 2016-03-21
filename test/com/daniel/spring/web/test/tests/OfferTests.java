package com.daniel.spring.web.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.daniel.spring.web.dao.CrudDao;
import com.daniel.spring.web.model.Offer;
import com.daniel.spring.web.model.Role;
import com.daniel.spring.web.model.User;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/daniel/spring/web/config/dao-context.xml", 
		"classpath:com/daniel/spring/web/test/config/datasource.xml",
		"classpath:com/daniel/spring/web/config/security-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class OfferTests {
	
	private static Offer offer;
	private static User user;
	
	static {
		User u = new User();
		u.setName("Daniel Moffat");
		u.setUsername("daniel");
		u.setPassword("password");
		u.setEmail("dan@lol.com");
		u.setEnabled(true);
		u.setAuthority(Role.ROLE_ADMIN);
		
		Offer o = new Offer();
		o.setText("This is a sick offer, dude.");
		o.setUser(u);
		
		offer = o;
		user = u;
		
		System.out.println(user);
		System.out.println(offer);
	}
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CrudDao<Offer, Integer> offerDao;
	
	@Autowired
	private DataSource dataSource;
	
	@Before
	public void init() {
		System.out.println("Setting up..");
		
		NamedParameterJdbcTemplate jdbc = new NamedParameterJdbcTemplate(dataSource);
		
		// Remove all existing users and offers before each test, remove offers first because of the FK.
		jdbc.update("delete from offer", new MapSqlParameterSource());
		jdbc.update("delete from users", new MapSqlParameterSource());
		
		// Create a dummy user since all offers require a user.
		MapSqlParameterSource userParams = new MapSqlParameterSource();
		userParams.addValue("username", user.getUsername());
		userParams.addValue("password", passwordEncoder.encode(user.getPassword()));
		userParams.addValue("email", user.getEmail());
		userParams.addValue("name", user.getName());
		userParams.addValue("authority", user.getAuthority().toString());		
		
		jdbc.update("insert into users (username, password, email, name, authority) values (:username, :password, :email, :name, :authority)", userParams);
	}
	
	@Test
	public void testAdd() {
		assertTrue("Offer creation should return true on success.", offerDao.add(offer));
	}
	
	@Test
	public void testDelete() {
		offerDao.add(offer);
		assertTrue("Offer deletion should return true on success.", offerDao.delete(offer));
	}
	
	@Test
	public void testUpdate() {
		offerDao.add(offer);
		offer.setText("This offer has changed...");
		assertTrue("Offer updates should return true on success.", offerDao.update(offer));
	}
	
	@Test
	public void testList() {
		offerDao.add(offer);
		List<Offer> offers = offerDao.list();
		
		System.out.println(offers);
		
		assertEquals("Should list all offers in the database", 1, offers.size());
	}
	
	@Test
	public void testListWithLimit() {
		offerDao.add(offer);
		int size = 0;
		List<Offer> offers = offerDao.list(size);
		assertEquals("Offer count should match the specified limit (if enough rows exist in the table)", size, offers.size());
	}
	
	@After
	public void destroy() {
		System.out.println("Cleaning up..");
	}
}
