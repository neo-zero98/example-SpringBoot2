package com.codmid.orderapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codmid.orderapi.entity.Product;
import com.codmid.orderapi.exceptions.GeneralServiceException;
import com.codmid.orderapi.exceptions.NoDataFoundException;
import com.codmid.orderapi.exceptions.ValidateServiceException;
import com.codmid.orderapi.repository.ProductRepository;
import com.codmid.orderapi.validators.ProductValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepo;
	
	public List<Product> findAll(Pageable page){
		try {
			List<Product> lista = this.productRepo.findAll(page).toList();
			return lista;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}
	
	public Product findById(Long id) {
		try {
			Product product = this.productRepo.findById(id)
					.orElseThrow(()->new NoDataFoundException("no existe el producto"));
			return product;
		} catch(ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		}catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}		
	}
	
	@Transactional
	public void delete(Long id) {
		try {
			Product product = this.productRepo.findById(id)
					.orElseThrow(()->new NoDataFoundException("no se puede eliminar porque no existe"));
			this.productRepo.delete(product);
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}
	
	@Transactional
	public Product save(Product product) {
		try {
			ProductValidator.validatorSave(product);
			
			if(product.getId() == null) {
				Product newProduct = this.productRepo.save(product);
				return newProduct;
			}
			Product updateProduct = this.productRepo.findById(product.getId())
					.orElseThrow(()->new NoDataFoundException("no se puede eliminar porque no existe"));
			updateProduct.setName(product.getName());
			updateProduct.setPrice(product.getPrice());
			this.productRepo.save(updateProduct);
			
			return updateProduct;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}
	
	@Transactional
	public Product create(Product product) {
		this.productRepo.save(product);
		return product;
	}

	@Transactional
	public Product update(Product product) {
		Product updateProduct = this.productRepo.findById(product.getId())
				.orElseThrow(()->new RuntimeException("no se puede eliminar porque no existe"));
		updateProduct.setName(product.getName());
		updateProduct.setPrice(product.getPrice());
		this.productRepo.save(updateProduct);
		
		return updateProduct;		
	}

}
