package centripio.ecommerce;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import centripio.ecommerce.entity.Customer;

public class Delete {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ecommerce-jpa");
		EntityManager em = factory.createEntityManager();
		
		em.getTransaction().begin();
		
		Customer customerDelete = em.find(Customer.class, 1L);
		em.remove(customerDelete);
		
		em.getTransaction().commit();

	}

}
