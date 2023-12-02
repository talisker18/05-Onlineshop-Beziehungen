package de.oio.dao;

import java.util.List;

import de.oio.domain.User;

public interface UserDao {
	User save(User u);
	User get(Long id);
	User delete(Long id);
	
	List<User> list();
}
