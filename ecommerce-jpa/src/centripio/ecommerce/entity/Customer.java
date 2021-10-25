package centripio.ecommerce.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import centripio.ecommerce.entity.enums.CustomerStatus;

@Entity
@Table(name="customers")
public class Customer {
	@Transient
	private String fullname = "yolo";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="first_name", length = 30, nullable = false)
	private String firstname;
	
	@Column(name="last_name", length = 50, nullable = false)
	private String lastname;
	
//	@Temporal(TemporalType.DATE)
	@Column(name = "birthday", nullable = false)
	private LocalDate birthday;
	
//	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="reg_date", nullable = false, updatable = false)	
//	private Calendar regDate = Calendar.getInstance();
	private LocalDateTime regDate = LocalDateTime.now();  
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private CustomerStatus status = CustomerStatus.ACTIVE; 
	
	@Embedded
	private Image avatar;
	
	public CustomerStatus getStatus() {
		return status;
	}
	public void setStatus(CustomerStatus status) {
		this.status = status;
	}
	public LocalDate getBirthday() {
		return birthday;
	}
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
