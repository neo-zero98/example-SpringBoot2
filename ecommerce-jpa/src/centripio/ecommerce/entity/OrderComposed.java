package centripio.ecommerce.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import centripio.ecommerce.entity.enums.Status;

@Entity
@Table(name = "orders_composed")
public class OrderComposed {
	
	@EmbeddedId
	private OrderPK id;
	
	@Column(name = "reg_date", nullable = false, updatable = false)
	private LocalDateTime regDate = LocalDateTime.now();
	
	@Column(name = "status", nullable = false, length = 10)
	@Enumerated(EnumType.STRING)
	private Status status = Status.ACTIVE;
	
	@ManyToOne
	@JoinColumn(name = "fk_customer", nullable = false, updatable = false)
	private Customer customer;	
	
	public OrderPK getId() {
		return id;
	}

	public void setId(OrderPK id) {
		this.id = id;
	}

	public LocalDateTime getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}	
	
	

}
