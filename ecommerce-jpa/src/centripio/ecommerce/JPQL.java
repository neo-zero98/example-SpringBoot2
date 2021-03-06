package centripio.ecommerce;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Tuple;

import centripio.ecommerce.entity.Order;
import orderapi.ecommerce.dto.OrderTotalDTO;

public class JPQL {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ecommerce-jpa");
		EntityManager em = factory.createEntityManager();
		
		/*
		em.createQuery("select ABS(o.total) from Order o")
			.getResultStream()
			.forEach(System.out::println);
		*/
		
		/*
		String name= "oscar";
		em.createQuery("select o from Order o where o.customer.firtname like :name", Order.class)
		.setParameter("name", "%" + name + "%")
		.getResultStream()
		.forEach(x -> System.out.println(x.getId()));
		*/
		
		/*
		em.createQuery("select TRIM(o.customer.firtname) from Order o", Order.class)
		.getResultStream()
		.forEach(x -> System.out.println(x.getId()));
		*/
		
		/*
		em.createQuery("select o from Order o where o.lines IS NOT EMPTY", Order.class)
			.getResultStream()
			.forEach(x -> System.out.println(x.getId()));
		*/
		
		/*
		em.createQuery("select o.id as id, size(o.lines) as lines from Order o", Tuple.class)
		.getResultStream()
		.forEach(x -> System.out.println(x.get("id") + ", " + x.get("lines")));
		*/
		
		
		/*
		em.createQuery("select e from Order e where e.id = :id or e.total > 100 and UPPER(e.customer.firtname) <> :name", Order.class)
			.setParameter("id", 1L)
			.setParameter("name", "rene")
			.getResultStream()
			.forEach(x -> System.out.println(x.getCustomer().getFirtname()));
			
		*/
		

		/*em.createQuery("select o from Order o where o.total between :start and :end", Order.class)
			.setParameter("start", 100d)
			.setParameter("end", 1000d)
			.getResultStream()
			.forEach(x -> System.out.println(x.getCustomer().getFirtname()));
		*/
		
		List<OrderTotalDTO> results = em.createQuery("select new orderapi.ecommerce.dto.OrderTotalDTO(o.customer.firstname as name, sum(o.total) as total) from Order o group by o.customer.firstname", OrderTotalDTO.class)
			.getResultList();
		
		for(OrderTotalDTO row: results) {
			System.out.println(row.getName() + ", " + row.getTotal());
		}

	}

}
