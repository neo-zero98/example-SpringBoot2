package centripio.ecommerce;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import centripio.ecommerce.entity.Customer;
import centripio.ecommerce.entity.enums.CustomerStatus;

public class Persist {

	public static void main(String[] args) {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("ecommerce-jpa");
		EntityManager em = emFactory.createEntityManager();
		
		em.getTransaction().begin();
		
		Customer customer1 = new Customer();
		customer1.setBirthday(LocalDate.now());
		customer1.setFirstname("Arnoldo");
		customer1.setLastname("Lopez");
		customer1.setStatus(CustomerStatus.ACTIVE);
		em.persist(customer1);
		System.out.println("id: "+customer1.getId());
		
		Customer customer2 = new Customer();
		customer2.setBirthday(LocalDate.now());
		customer2.setFirstname("Serafin");
		customer2.setLastname("Lopez");
		customer2.setStatus(CustomerStatus.INACTIVE);
		em.persist(customer2);
		
		em.getTransaction().commit();
		
		
	}

}
