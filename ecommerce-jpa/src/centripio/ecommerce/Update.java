package centripio.ecommerce;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import centripio.ecommerce.entity.Customer;
import centripio.ecommerce.entity.enums.CustomerStatus;

public class Update {

	public static void main(String[] args) {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("ecommerce-jpa");
		EntityManager em = emFactory.createEntityManager();
		
//		em.getTransaction().begin();
//		
//		Customer customer = em.find(Customer.class, 1L);
//		customer.setFirstname("Kenia");
//		customer.setLastname("Robles");
//		em.persist(customer);
//		
//		em.getTransaction().commit();
		
		//otra manera de actualizar(recomendada)
		em.getTransaction().begin();
	
		Customer customer = new Customer();
		customer.setId(1L);
		customer.setFirstname("Kenia");
		customer.setLastname("Hernández");
		customer.setBirthday(LocalDate.now());
		customer.setStatus(CustomerStatus.INACTIVE);
		em.merge(customer);
		
		em.getTransaction().commit();
		
		
	}

}
