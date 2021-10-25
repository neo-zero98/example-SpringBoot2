package centripio.ecommerce;

import java.io.InputStream;
import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import centripio.ecommerce.entity.Customer;
import centripio.ecommerce.entity.Image;
import centripio.ecommerce.entity.Product;
import centripio.ecommerce.entity.enums.CustomerStatus;

public class Binarios {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ecommerce-jpa");
		EntityManager em = factory.createEntityManager();
		
		em.getTransaction().begin();
		try(InputStream stream = Binarios.class.getClassLoader().getResourceAsStream("META-INF/galletas.jpg")) {
			int fileLength = stream.available();
			byte[] bytes = new byte[fileLength];
			stream.read(bytes);
			
			Image image = new Image();
			image.setImage(bytes);
			image.setFile_length((long)fileLength);
			image.setFile_name("galletas.jpg");
			image.setFile_type("image/jpeg");			
			
			Product product = new Product();
			product.setImage(image);
			product.setName("producto con imagen");
			product.setPrice(100d);
			em.persist(product);	
			
			Customer customer = new Customer();
			customer.setFirstname("Manuel");
			customer.setLastname("Rivera");
			customer.setBirthday(LocalDate.now());
			em.persist(customer);
		} catch (Exception e) {
			
		}
		
		em.getTransaction().commit();
		/*
		Product productImage = em.createNamedQuery("Product.findByName", Product.class)
			.setParameter("name", "producto con imagen")
			.getSingleResult();
		
		System.out.println(productImage.getId() + " , " + productImage.getImage().length);
		*/
	}

}
