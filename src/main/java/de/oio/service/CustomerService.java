package de.oio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.oio.dao.CustomerDao;
import de.oio.dao.UserDao;
import de.oio.domain.Customer;
import de.oio.domain.User;

@Service
public class CustomerService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private CustomerDao customerDao;

	public void add(Customer c, User u) {
		this.customerDao.save(c);
		this.userDao.save(u);
	}
}
