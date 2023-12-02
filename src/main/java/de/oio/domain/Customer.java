package de.oio.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "KUNDE")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name ="VORNAME", nullable = false, length = 100)
	private String firstName;
	
	@Column(name ="NACHNAME", nullable = false, length = 100)
	private String lastName;
	
	@Temporal(TemporalType.DATE)
	@Column(name ="ERSTELLUNGSDATUM")
	private Date creationDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name ="ZULETZT_GEAENDERT")
	private Date changeDate;
	
	@Column(name ="KUNDEN_TYP")
	private CustomerType customerType;
	
	@Transient
	private Date readDate;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL) //bidirectional
	private Set<Address> addresses = new HashSet<Address>();
	
	@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
	private User user;

	public Customer() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}

	public CustomerType getCustomerType() {
		return customerType;
	}

	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
	}

	public Date getReadDate() {
		return readDate;
	}

	public void setReadDate(Date readDate) {
		this.readDate = readDate;
	}

	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}
	
	public void addAddress(Address a) {
		this.addresses.add(a);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", creationDate="
				+ creationDate + ", changeDate=" + changeDate + ", customerType=" + customerType + ", readDate="
				+ readDate + "]";
	}
	
	@PrePersist
	protected void onCreate() {
		this.creationDate = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.changeDate = new Date();
	}
}
