package de.oio.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import de.oio.domain.Customer;
import de.oio.domain.Order;

@Repository
@Transactional //gilt nur für public methoden -> alle methoden verwenden dann transactions
//gehört auch zu AOP
public class OrderDaoImpl implements OrderDao {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private JpaTemplate jpaTemplate;

	@Override
	public Order save(Order o) {
		if (o.getId() == null) {
			jpaTemplate.persist(o);
//			em.persist(u);
		} else {
			em.merge(o);
		}
		
		//hier könnte man das obj mit settern verändern, damit es autom. in DB gespeichert wird (wir sind immer noch im persistence context -> dirty checking greift)
		
		return o;
	}

	@Override
	@Transactional(readOnly = true) //readonly true -> macht eine transaction, aber kein commit. kann verwendet werden wenn keine daten manipuliert werden (performance boost)
	public Order get(Long id) {
		return em.find(Order.class, id);
	}

	@Override
	public Order delete(Long id) {
		Order o = this.get(id);
		if (o != null) {
			em.remove(o);
		}
		return o;
	}
}
