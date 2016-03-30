package com.daniel.spring.web.test.tests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.sql.DataSource;

import org.hibernate.criterion.Order;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.daniel.spring.web.dao.HibernateCrudDao;
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
	private HibernateCrudDao<Offer, Integer> offerDao;
	
	@Autowired
	private HibernateCrudDao<User, String> userDao;
	
	@Autowired
	private DataSource dataSource;
	
	@Before
	public void init() {	
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		
		// Remove all existing users and offers before each test, remove offers first because of the FK constraint.
		jdbc.update("delete from offer");
		jdbc.update("delete from users");
		
		// Create a dummy user since all offers require a user.
		userDao.add(user);
	}
	
	@Test
	public void testAdd() {
		assertEquals("Should return the primary key when added.", Integer.valueOf(1), offerDao.add(offer));
	}
	
	@Test
	public void testDelete() {
		offerDao.add(offer);
		assertEquals("Should insert offer", 1, offerDao.list().size());
		
		offerDao.delete(offer);
		assertEquals("Should delete an offer", 0, offerDao.list().size());
	}
	
	@Test
	public void testUpdate() {
		Integer id = offerDao.add(offer);
		String updatedText = "This offer has been updated!";
		
		// Update the name - doesn't support updating the primary key (username) right now!
		offer.setText(updatedText);		
		offerDao.update(offer);
		Offer o = offerDao.retrieve(id);
		
		assertEquals("Should update an offer", updatedText, o.getText());
	}
	
	@Test
	public void testList() {
		assertEquals("Offer database should be empty", 0, offerDao.list().size());
		offerDao.add(offer);
		assertEquals("Should list all offers in the database", 1, offerDao.list().size());
	}
	
	@Test
	public void testListWithLimit() {
		Offer o = new Offer(user, "This is another offer.");
		Offer o2 = new Offer(user, "This is the third offer.");
		
		offerDao.add(o);
		offerDao.add(o2);
		
		assertEquals("Should limit returned rows", 1, offerDao.list(1).size());
	}
	
	@Test
	public void testOrderByText() {
		Offer o = new Offer(user, "ZZ man offer.");
		
		offerDao.add(offer);
		offerDao.add(o);
		
		List<Offer> oo = offerDao.orderBy(Order.asc("text"));
		
		assertEquals("Should order the offers by text", offer.getText(), oo.get(0));
	}
}
