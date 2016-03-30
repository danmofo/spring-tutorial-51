package com.daniel.spring.web.test.tests;

import static org.junit.Assert.assertEquals;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.daniel.spring.web.dao.HibernateCrudDao;
import com.daniel.spring.web.dao.impl.HibernateOfferDaoImpl;
import com.daniel.spring.web.model.Offer;
import com.daniel.spring.web.model.Role;
import com.daniel.spring.web.model.User;

/**
 * Test bed for Hibernate, allows me to see what SQL queries Hibernate is executing.
 * 
 * @author danielmoffat
 *
 */
@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/daniel/spring/web/config/dao-context.xml", 
		"classpath:com/daniel/spring/web/test/config/datasource.xml",
		"classpath:com/daniel/spring/web/config/security-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class HibernateTests {
		
	private static final Logger logger = LogManager.getLogger(HibernateTests.class);
	
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
	private HibernateCrudDao<Offer, Integer> offerDao;
	
	@Autowired
	private HibernateCrudDao<User, String> userDao;
	
	@Autowired
	private DataSource dataSource;
	
	@Before
	public void init() {	
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		
		jdbc.update("delete from offer");
		jdbc.update("delete from users");
		
		userDao.add(user);
		offerDao.add(offer);
	}
	
	@Test
	public void testRetrieve() {
		
		System.out.println(userDao.list());
		
	}
}
