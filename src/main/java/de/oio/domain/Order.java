package de.oio.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "BESTELLUNG")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date orderTime;
	
	@Transient
	private double totalAmount;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_fk")
	private Customer customer;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
	        name = "bestellung_article", 
	        joinColumns = { @JoinColumn(name = "bestellung_id") }, 
	        inverseJoinColumns = { @JoinColumn(name = "article_id") }
	        )
	private Set<Article> articles = new HashSet<Article>();

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public void addArticle(Article a) {
		this.articles.add(a);
	}
}
