package centripio.ecommerce;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import centripio.ecommerce.entity.Customer;
import centripio.ecommerce.entity.Order;
import centripio.ecommerce.entity.OrderLine;
import centripio.ecommerce.entity.Payment;
import centripio.ecommerce.entity.enums.PayMethod;

public class OneToOne {

	public static void main(String[] args) {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("ecommerce-jpa");
		EntityManager em = emFactory.createEntityManager();

		em.getTransaction().begin();

		Customer customer1 = em.find(Customer.class, 1L);
		Order order = new Order();
		order.setCustomer(customer1);
		
		em.persist(order);

		for (int i = 0; i < 10; i++) {
			OrderLine line = new OrderLine();
			//line.setProduct("Product " + (i + 1));
			line.setQuantity(i + 1d);
			line.setUnitPrice((i + 1) * 10d);
			order.addLines(line);

			em.persist(line);
		}
		
		Payment payment = new Payment();
		payment.setAmount(order.getTotal());
		payment.setPayMethod(PayMethod.DEBIT);
		order.setPayment(payment);
		
		em.persist(payment);
		em.persist(order);

		em.getTransaction().commit();
		
//		em.detach(payment);
//		em.detach(order);
	}

}
