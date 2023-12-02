package de.oio.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.oio.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/application-context.xml" })
public class UserDaoTest {

	@Autowired
	private UserDao userDao;

	@Before
	public void setUp() throws Exception {
		assertNotNull(userDao);
	}

	/*@Test
	public void testSave() {
		assertEquals("duke",
				userDao.save(new User("duke", "secret", "duke@oracle.com"))
						.getUsername());
		assertEquals(
				"ddevelop",
				userDao.save(
						new User("ddevelop", "geheim", "dieter.develop@oio.de"))
						.getUsername());
	}*/

	@Test
	public void testGet() {
		assertNotNull(userDao.get(1L));
		assertEquals("geheim", userDao.get(2L).getPassword());
	}

	@Test
	public void testUpdate() {
		User u = userDao.get(2L);
		u.setUsername("d.develop");
		assertEquals("d.develop", userDao.save(u).getUsername());
	}

	@Test
	public void testDelete() {
		assertEquals("d.develop", userDao.delete(2L).getUsername());
	}

	@Test
	public void testList() {
		List<User> users = userDao.list();
		assertEquals(1, users.size());
		assertEquals("duke", users.get(0).getUsername());
	}
}
