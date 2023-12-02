package de.oio.main;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.oio.dao.CustomerDao;
import de.oio.dao.OrderDao;
import de.oio.domain.Address;
import de.oio.domain.Article;
import de.oio.domain.Country;
import de.oio.domain.Customer;
import de.oio.domain.CustomerType;
import de.oio.domain.Order;
import de.oio.domain.User;
import de.oio.service.CustomerService;

public class Main {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
		//UserDao userDao = ctx.getBean("userDaoImpl", UserDao.class);
		//userDao.save(new User("otto", "hansen", "otto@oio.de"));
		
		CustomerDao customerDao = ctx.getBean("customerDaoImpl", CustomerDao.class);
		
		Country country1 = new Country();
		country1.setName("Schweiz");
		Country country2 = new Country();
		country2.setName("Deutschland");
		
		Address a1 = new Address();
		a1.setStreet("street1");
		a1.setZipCode(1234);
		a1.setCity("city1");
		a1.setCountry(country1);
		
		Address a2 = new Address();
		a2.setStreet("street2");
		a2.setZipCode(5678);
		a2.setCity("city2");
		a2.setCountry(country2);
		
		//country1 und country2 zuerst nochmal laden und dann in die neuen Adressen einfügen. Folgender Code funktioniert nicht, da dann country1 und country2 detached sind
		//Address a3 = new Address("street3", 123, "city3", country1);
		//Address a4 = new Address("street4", 567, "city4", country2);
		
		User u1 = new User();
		u1.setUsername("username1");
		u1.setPassword("password1");
		u1.setEmail("email1");
		
		Customer c1 = new Customer();
		c1.setFirstName("vorname1");
		c1.setLastName("nachname1");
		c1.setCustomerType(CustomerType.NEWUSER);
		c1.addAddress(a1);
		c1.addAddress(a2);
		
		a1.setCustomer(c1);
		a2.setCustomer(c1);
		
		u1.setCustomer(c1);
		c1.setUser(u1);
		
		//Customer c2 = new Customer("vorname2", "nachname2", CustomerType.PREMIUM, set2);
		//customerDao.save(c1);
		//customerDao.save(c2);
		
		//c1.setFirstName("vorname1 update");
		//customerDao.save(c1);
		
		Order o1 = new Order();
		o1.setOrderTime(new Date());
		o1.setTotalAmount(10);
		o1.setCustomer(c1);
		
		Article art1 = new Article();
		art1.setName("name1");
		art1.setDescription("descr1");
		art1.setNumber("number1");
		art1.setPrice(new BigDecimal(17.6));
		
		Article art2 = new Article();
		art2.setName("name2");
		art2.setDescription("descr2");
		art2.setNumber("number2");
		art2.setPrice(new BigDecimal(55.4));
		
		o1.addArticle(art1);
		o1.addArticle(art2);
		
		OrderDao orderDao = ctx.getBean("orderDaoImpl", OrderDao.class);
		orderDao.save(o1);
		
		//folgendes ist nicht mehr nötig, das wird nun via OrderDao gespeichert
		//CustomerService service = ctx.getBean("customerService", CustomerService.class);
		
		//service.add(c1, u1);
	}
}
