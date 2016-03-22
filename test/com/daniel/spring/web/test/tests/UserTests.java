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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	private PasswordEncoder passwordEncoder;
		
	@Autowired
	private CrudDao<User, String> userDao;
	
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
		assertTrue("Offer creation should return true on success.", userDao.add(user));
	}
	
	@Test
	public void testDelete() {
		User retrievedUser = null;
		userDao.add(user);
		
		for(User u : userDao.list()) {
			retrievedUser = u;
		}
		
		assertTrue("User deletion should return true on success.", userDao.delete(retrievedUser));
	}
	
	@Test
	public void testUpdate() {
		User retrievedUser = null;
		userDao.add(user);
		
		for(User u : userDao.list()) {
			retrievedUser = u;
		}
				
		retrievedUser.setName("Bobby Tables");

		assertTrue("Offer updates should return true on success.", userDao.update(retrievedUser));
	}
	
	@Test
	public void testList() {
		userDao.add(user);
		List<User> users = userDao.list();
				
		assertEquals("Should list all users in the database", 1, users.size());
	}
	
	@Test
	public void testListWithLimit() {
		userDao.add(user);
		int size = 0;
		List<User> users = userDao.list(size);
		assertEquals("User count should match the specified limit (if enough rows exist in the table)", size, users.size());
	}
	
	@After
	public void destroy() {
		System.out.println("All done!");
		System.out.println(userDao.list());
	}
}
