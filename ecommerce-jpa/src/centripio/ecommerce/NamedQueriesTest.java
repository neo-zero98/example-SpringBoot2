package centripio.ecommerce;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Tuple;

import centripio.ecommerce.entity.Order;

public class NamedQueriesTest {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ecommerce-jpa");
		EntityManager em = factory.createEntityManager();
		/*
		em.createNamedQuery("Order.orderByCustomer", Order.class)
			.setParameter("customerID", 1L)
			.getResultStream()
			.forEach(x -> System.out.println(x.getId()));
		*/
		/*
		em.createNamedQuery("Order.orderByCustomerNative", Tuple.class)
			.setParameter("cutomerID", 1L)
			.getResultStream()
			.forEach(x -> System.out.println(x.get("id")));
		*/
		em.createNamedQuery("Order.findActiveOrders", Order.class)
		.getResultStream()
		.forEach(x-> System.out.println(x.getStatus()));
	}

}
