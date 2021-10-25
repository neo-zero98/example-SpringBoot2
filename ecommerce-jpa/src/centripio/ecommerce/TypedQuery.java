package centripio.ecommerce;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Tuple;

import centripio.ecommerce.entity.Order;

public class TypedQuery {

	public static void main(String[] args) {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("ecommerce-jpa");
		EntityManager em = emFactory.createEntityManager();
		
//		Query query = em.createQuery("select o from Order o");
//		List<Order> orders = query.getResultList();
//		orders.stream().forEach(x -> System.out.println(x.getId()));
		
//		Query query = em.createQuery("select o from Order o where o.id = :id");
//		query.setParameter("id", 100L);
//		List<Order> orders = query.getResultList();
//		orders.stream().forEach(x -> System.out.println(x.getId()));
		
//		em.createQuery("select o from Orders o where o.id = :id and size(o.lines) > :lines", Order.class)
//					.setParameter("id", 1L)
//					.setParameter("lines", 0)
//					.getResultList()
//					.stream()
//					.forEach( x -> System.out.println(x.getId()));
		
//		List<Tuple> results = em.createQuery("select o.id as id, o.total as total from Order o ", Tuple.class)
//				.getResultList();
//		for(Tuple row : results) {
//			System.out.println(row.get("id")+" , " + row.get("total"));
//		}
		
//		Query nativo 
		Object result = em.createNativeQuery("select now()")
				.getSingleResult();
		System.out.println(result);
		

	}

}
