package centripio.ecommerce;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import centripio.ecommerce.entity.Product;

public class Callbacks {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ecommerce-jpa");
		EntityManager em = factory.createEntityManager();
		

		em.getTransaction().begin();
		
		Product prod = em.find(Product.class, 1L);
		prod.setPrice(100d);
		em.persist(prod);
		
		em.getTransaction().commit();
	}

}
