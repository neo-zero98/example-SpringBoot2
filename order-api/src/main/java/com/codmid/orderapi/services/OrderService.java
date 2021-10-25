package com.codmid.orderapi.services;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.codmid.orderapi.entity.Order;
import com.codmid.orderapi.entity.OrderLine;
import com.codmid.orderapi.entity.Product;
import com.codmid.orderapi.entity.User;
import com.codmid.orderapi.exceptions.GeneralServiceException;
import com.codmid.orderapi.exceptions.NoDataFoundException;
import com.codmid.orderapi.exceptions.ValidateServiceException;
import com.codmid.orderapi.repository.OrderLineRepository;
import com.codmid.orderapi.repository.OrderRepository;
import com.codmid.orderapi.repository.ProductRepository;
import com.codmid.orderapi.security.UserPrincipal;
import com.codmid.orderapi.validators.OrderValidator;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private OrderLineRepository orderLineRepo;
	
	@Autowired
	private ProductRepository productRepo;

	public List<Order> findAll(Pageable page){
		try {
			return orderRepo.findAll(page).toList();
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}
	
	public Order findById(Long id) {
		try {
			return orderRepo.findById(id)
					.orElseThrow(() -> new NoDataFoundException("La orden no existe"));
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}
	
	public void delete(Long id) {
		try {
			Order order = findById(id);
			orderRepo.delete(order);
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}
	
	@Transactional
	public Order save(Order order) {
		try {
			OrderValidator.save(order);
			
			UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			User user = principal.getUser();
			
			double total = 0;
			for(OrderLine line : order.getLines()) {
				Product product = productRepo.findById(line.getProduct().getId())
					.orElseThrow(() -> new NoDataFoundException("No existe el producto " + line.getProduct().getId()));
				
				line.setPrice(product.getPrice());
				line.setTotal(product.getPrice() * line.getQuantity());
				total += line.getTotal();
			}
			order.setTotal(total);
			
			order.getLines().forEach(line -> line.setOrder(order));
			if(order.getId() == null) {
				order.setUser(user);
				order.setRegDate(LocalDateTime.now());
				return this.orderRepo.save(order);
			}
			Order savedOrder = findById(order.getId());
			order.setRegDate(savedOrder.getRegDate());
			List<OrderLine> deletedLine = savedOrder.getLines();
			deletedLine.removeAll(order.getLines());
			this.orderLineRepo.deleteAll(deletedLine);
			return this.orderRepo.save(order);
			
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}
}
