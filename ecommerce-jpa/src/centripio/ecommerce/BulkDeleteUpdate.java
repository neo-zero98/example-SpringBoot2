package centripio.ecommerce;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import centripio.ecommerce.entity.enums.Status;

public class BulkDeleteUpdate {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ecommerce-jpa");
		EntityManager em = factory.createEntityManager();

		em.getTransaction().begin();
		
		/*
		
		List<Order> orders = em.createQuery("select o from Order o", Order.class)
			.getResultList();
		
		
		for(Order order : orders) {
			order.setTotal(1000d);
			em.persist(order);
		}
		
		
		*/
		
		
		int updates = em.createQuery("update Order set total = total + :total, status= :status")
			.setParameter("status", Status.INNACTIVE)
			.setParameter("total", 1000d)
			.executeUpdate();
		System.out.println("update => " + updates);
		
		/*
		int updates1 = em.createQuery("delete from OrderLine")
				.executeUpdate();
		int updates2 = em.createQuery("delete from Order")
				.executeUpdate();
			System.out.println("update => " + updates1 + ", " + updates2);
		*/
		
		em.getTransaction().commit();
	}

}
