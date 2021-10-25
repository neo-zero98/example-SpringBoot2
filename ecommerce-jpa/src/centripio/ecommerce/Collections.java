package centripio.ecommerce;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import centripio.ecommerce.entity.Product;

public class Collections {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ecommerce-jpa");
		EntityManager em = factory.createEntityManager();
		
		em.createQuery("select p from Order o inner join o.lines l inner join l.product p",Product.class)
					.getResultStream()
					.forEach(x -> System.out.println(x.getName()));;

	}

}
