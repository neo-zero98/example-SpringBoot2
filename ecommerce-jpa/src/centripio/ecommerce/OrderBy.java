package centripio.ecommerce;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import centripio.ecommerce.entity.Order;
import centripio.ecommerce.entity.OrderLine;

public class OrderBy {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ecommerce-jpa");
		EntityManager em = factory.createEntityManager();
		
		Order order = em.find(Order.class, 1L);
		order.getLines().get(0);
		
		for(OrderLine line: order.getLines()) {
			System.out.println(line.getCtr());
		}
	}

}
