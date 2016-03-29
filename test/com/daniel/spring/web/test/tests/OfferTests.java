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
	}
	
	private static Offer offer;
	private static User user;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CrudDao<Offer, Integer> offerDao;
	
	@Autowired
	private CrudDao<User, String> userDao;
	
	@Autowired
	private DataSource dataSource;
	
	@Before
	public void init() {	
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
		assertEquals("Offer creation should return the primary key on success.", Integer.valueOf(1),  offerDao.add(offer));
	}
	
	@Test
	public void testDelete() {
		Offer retrievedOffer = null;
		offerDao.add(offer);
		
		for(Offer o : offerDao.list()) {
			retrievedOffer = o;
		}
		
		assertTrue("Offer deletion should return true on success.", offerDao.delete(retrievedOffer));
	}
	
	@Test
	public void testUpdate() {
		Offer retrievedOffer = null;
		offerDao.add(offer);
		
		// I'm almost certain there is a better way to get the offer ID after inserting, whilst still preserving the boolean return
		// value. This will only work for a single offer in the table.
		for(Offer o : offerDao.list()) {
			retrievedOffer = o;
		}
				
		retrievedOffer.setText("This offer has been updated.");

		assertTrue("Offer updates should return true on success.", offerDao.update(retrievedOffer));
	}
	
	@Test
	public void testList() {
		offerDao.add(offer);
		List<Offer> offers = offerDao.list();
				
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
		System.out.println("All done!");
		System.out.println(offerDao.list());
	}
}
