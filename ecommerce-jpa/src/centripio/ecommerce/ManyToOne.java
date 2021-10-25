package centripio.ecommerce;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import centripio.ecommerce.entity.Customer;
import centripio.ecommerce.entity.Order;
import centripio.ecommerce.entity.enums.Status;

public class ManyToOne {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ecommerce-jpa");
		EntityManager em = factory.createEntityManager();
		
//		em.getTransaction().begin();
//		
//		Customer customer1 = em.find(Customer.class, 1L);
//		Order order = new Order();
//		order.setCustomer(customer1);
//		order.setRegDate(LocalDateTime.now());
//		order.setStatus(Status.ACTIVE);
//		order.setTotal(100d);
//		
//		em.persist(order);
//		
//		
//		em.getTransaction().commit();
		
		Order order = em.find(Order.class, 1L);
		System.out.println(order.getTotal() + " , " + order.getCustomer().getFirstname());
	}

}
