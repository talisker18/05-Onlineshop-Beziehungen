package de.oio.dao;

import de.oio.domain.Order;

public interface OrderDao {
	Order save(Order o);
	Order get(Long id);
	Order delete(Long id);
}
