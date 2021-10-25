package centripio.ecommerce;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import centripio.ecommerce.entity.Customer;

public class HashcodeEquals {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ecommerce-jpa");
		EntityManager em = factory.createEntityManager();
		
		Customer customer1 = em.find(Customer.class, 1L);
		em.detach(customer1);
		Customer customer2 = em.find(Customer.class, 1L);
		
		System.out.println("equals? "+customer1.equals(customer2));
		
	}

}
