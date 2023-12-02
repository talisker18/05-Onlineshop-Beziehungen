package de.oio.dao;

import de.oio.domain.Customer;

public interface CustomerDao {
	Customer save(Customer u);
	Customer get(Long id);
	Customer delete(Long id);
}
