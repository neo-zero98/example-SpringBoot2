package centripio.ecommerce;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.mysql.cj.Query;

public class QuerySentence {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ecommerce-jpa");
		EntityManager em = factory.createEntityManager();
		
		//JPQL = Java Persistence Query Selector

	}

}
