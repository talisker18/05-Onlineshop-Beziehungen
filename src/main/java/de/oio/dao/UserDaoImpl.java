package de.oio.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import de.oio.domain.User;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private JpaTemplate jpaTemplate;

	@Override
	public User save(User u) {
		if (u.getId() == null) {
			jpaTemplate.persist(u);
//			em.persist(u);
		} else {
			em.merge(u);
		}
		return u;
	}

	@Override
	@Transactional(readOnly = true)
	public User get(Long id) {
		return em.find(User.class, id);
	}

	@Override
	public User delete(Long id) {
		User u = this.get(id);
		if (u != null) {
			em.remove(u);
		}
		return u;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> list() {
		return jpaTemplate.find("select u from User u");
//		return em.createQuery("select u from de.oio.domain.User u", User.class).getResultList();
	}
}
