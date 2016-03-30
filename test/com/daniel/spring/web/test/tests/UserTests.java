package com.daniel.spring.web.test.tests;

import static org.junit.Assert.*;

import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.criterion.Order;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.daniel.spring.web.dao.HibernateCrudDao;
import com.daniel.spring.web.dao.impl.HibernateOfferDaoImpl;
import com.daniel.spring.web.model.Role;
import com.daniel.spring.web.model.User;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/daniel/spring/web/config/dao-context.xml", 
		"classpath:com/daniel/spring/web/test/config/datasource.xml",
		"classpath:com/daniel/spring/web/config/security-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserTests {
	
	private static final Logger logger = LogManager.getLogger(UserTests.class);
		
	static {
		User u = new User();
		u.setName("Daniel Moffat");
		u.setUsername("daniel");
		u.setPassword("password");
		u.setEmail("dan@lol.com");
		u.setEnabled(true);
		u.setAuthority(Role.ROLE_ADMIN);
				
		user = u;
	}
	
	private static User user;
			
	@Autowired
	private HibernateCrudDao<User, String> userDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private DataSource dataSource;
	
	@Before
	public void init() {	
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		
		jdbc.update("delete from offer");
		jdbc.update("delete from users");
	}
	
	@Test
	public void testAdd() {
		assertEquals("Should return the primary key when added.", user.getUsername(), userDao.add(user));
	}
	
	@Test
	public void testDelete() {
		userDao.add(user);
		assertEquals("Should insert user", 1, userDao.list().size());
		
		userDao.delete(user);
		assertEquals("Should delete a user", 0, userDao.list().size());
	}
	
	@Test
	public void testUpdate() {
		String username = userDao.add(user);
		
		// Update the name - doesn't support updating the primary key (username) right now!
		user.setName("Surf Monster");		
		userDao.update(user);
		User u = userDao.retrieve(username);
		assertEquals("Should update a user", "Surf Monster", u.getName());
	}
	
	@Test
	public void testList() {
		assertEquals("User database should be empty", 0, userDao.list().size());
		userDao.add(user);
		assertEquals("Should list all users in the database", 1, userDao.list().size());
	}
	
	@Test
	public void testListWithLimit() {
		User anotherUser = new User();
		anotherUser.setName("Funman");
		anotherUser.setUsername("the_king");
		anotherUser.setPassword("nopassword");
		anotherUser.setEmail("daniel@testing.com");
		anotherUser.setEnabled(true);
		anotherUser.setAuthority(Role.ROLE_ADMIN);
		
		User aFinalUser = new User();
		aFinalUser.setName("F");
		aFinalUser.setUsername("dan2");
		aFinalUser.setPassword("nopassword");
		aFinalUser.setEmail("daniel@foo.bar.com");
		aFinalUser.setEnabled(true);
		aFinalUser.setAuthority(Role.ROLE_ADMIN);
		
		userDao.add(user);
		userDao.add(anotherUser);
		userDao.add(aFinalUser);
		
		assertEquals("Should limit returned rows", 1, userDao.list(1).size());
	}
	
	@Test
	public void testOrderByName() {
		User anotherUser = new User();
		anotherUser.setName("Z");
		anotherUser.setUsername("the_king");
		anotherUser.setPassword("nopassword");
		anotherUser.setEmail("zaniel@testing.com");
		anotherUser.setEnabled(true);
		anotherUser.setAuthority(Role.ROLE_ADMIN);
		
		userDao.add(user);
		userDao.add(anotherUser);
		
		List<User> ou = userDao.orderBy(Order.desc("name"));
		List<User> ou2 = userDao.orderBy(Order.asc("email"));
		
		assertEquals("Should order the users by name", anotherUser.getName(), ou.get(0).getName());
		assertEquals("Should order the users by email", user.getEmail(), ou2.get(0).getEmail());
	}
	
	@Test
	public void testEncodePassword() {
		// user is static so other tests have set the password
		user.setPassword("password");
		userDao.add(user);
		boolean result = passwordEncoder.matches("password", user.getPassword());
		
		assertTrue("Should encode user's password", result);
	}
}
