package de.oio.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import de.oio.domain.Customer;

@Repository
@Transactional //gilt nur für public methoden -> alle methoden verwenden dann transactions
//gehört auch zu AOP
public class CustomerDaoImpl implements CustomerDao {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private JpaTemplate jpaTemplate;

	@Override
	public Customer save(Customer c) {
		if (c.getId() == null) {
			jpaTemplate.persist(c);
//			em.persist(u);
		} else {
			em.merge(c);
		}
		
		//hier könnte man das obj mit settern verändern, damit es autom. in DB gespeichert wird (wir sind immer noch im persistence context -> dirty checking greift)
		
		return c;
	}

	@Override
	@Transactional(readOnly = true) //readonly true -> macht eine transaction, aber kein commit. kann verwendet werden wenn keine daten manipuliert werden (performance boost)
	public Customer get(Long id) {
		return em.find(Customer.class, id);
	}

	@Override
	public Customer delete(Long id) {
		Customer c = this.get(id);
		if (c != null) {
			em.remove(c);
		}
		return c;
	}
}
