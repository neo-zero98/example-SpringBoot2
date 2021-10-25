package centripio.ecommerce;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import centripio.ecommerce.entity.Order;

public class LazyEaderLoading {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ecommerce-jpa");
		EntityManager em = factory.createEntityManager();
		
		Order order = em.find(Order.class, 1L);
		System.out.println(order.getCustomer().getFirstname());
		
		order.getLines().get(0).getProduct();
		

	}

}
