package com.daniel.spring.web.test.tests;

import static org.junit.Assert.*;

import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.daniel.spring.web.dao.CrudDao;
import com.daniel.spring.web.model.Role;
import com.daniel.spring.web.model.User;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/daniel/spring/web/config/dao-context.xml", 
		"classpath:com/daniel/spring/web/test/config/datasource.xml",
		"classpath:com/daniel/spring/web/config/security-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserTests {
	
	private static User user;
	
	static {
		User u = new User();
		u.setName("Daniel Moffat");
		u.setUsername("daniel");
		u.setPassword("password");
		u.setEmail("dan@lol.com");
		u.setEnabled(true);
		u.setAuthority(Role.ROLE_ADMIN);
		
		user = u;
		
		System.out.println(user);
	}
	
	@Autowired
	private CrudDao<User, String> userDao;
	
	@Autowired
	private DataSource dataSource;
	
	@Before
	public void init() {
		System.out.println("Setting up");
		
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		
		jdbc.execute("delete from users");
		jdbc.execute("delete from authorities");
	}
	
	@Test
	public void testAddUser() {
		userDao.add(UserTests.user);
		
		assertEquals("User should be added to the database.", 1, userDao.list().size());
	}
	
	@Test
	public void testListUsers() {
		userDao.add(UserTests.user);
		List<User> users = userDao.list();
				
		assertEquals("Should list the correct amount of users.", 1, users.size());
	}
	
	@Test
	public void testUpdateUser() {
		User u = new User();
		u.setUsername("daniel");
		u.setPassword("password");
		u.setEmail("dan@lol.com");
		u.setEnabled(true);
		u.setAuthority(Role.ROLE_ADMIN);
		
		// Add a user
		userDao.add(u);
		
		// Modify some properties
		u.setEnabled(false);
		
		// Check it worked
		assertTrue("Update should return true when successful.", userDao.update(u));
		
		// verify
		assertEquals("User should have been updated", false, userDao.retrieve("daniel").isEnabled());
	}
	
	@After
	public void destroy() {
		System.out.println("Cleaning up..");
	}
}
