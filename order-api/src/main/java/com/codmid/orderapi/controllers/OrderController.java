package com.codmid.orderapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codmid.orderapi.converters.OrderConverter;
import com.codmid.orderapi.dtos.OrderDTO;
import com.codmid.orderapi.entity.Order;
import com.codmid.orderapi.services.OrderService;
import com.codmid.orderapi.utils.WrapperResponse;

@RestController
public class OrderController {
	//OrderConverter converter = new OrderConverter();
	@Autowired
	private OrderConverter converter;
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping(value = "/orders")
	public ResponseEntity<WrapperResponse<List<OrderDTO>>> findAll(
			@RequestParam(name = "pageNumber", required = false, defaultValue = "0") int pageNumber,
			@RequestParam(name = "pageSize", required = false, defaultValue = "5") int pageSize
			){
		Pageable page = PageRequest.of(pageNumber, pageSize);
		List<Order> orders = orderService.findAll(page);
		return new WrapperResponse<>(true, "success" , this.converter.fromEntity(orders))
				.createResponse();		
	}
	
	@GetMapping(value = "/orders/{id}")
	public ResponseEntity<WrapperResponse<OrderDTO>> findById(@PathVariable(name = "id") Long id){
		Order order = orderService.findById(id);		
		return new WrapperResponse<>(true, "success" , this.converter.fromEntity(order))
				.createResponse();	
	}
	
	@PostMapping(value = "/orders")
	public ResponseEntity<WrapperResponse<OrderDTO>> create(@RequestBody OrderDTO orderDto){
		Order order = orderService.save(converter.fromDTO(orderDto));
		return new WrapperResponse<>(true, "success" , this.converter.fromEntity(order))
				.createResponse();
	}
	
	@PutMapping(value = "/orders")
	public ResponseEntity<WrapperResponse<OrderDTO>> update(@RequestBody OrderDTO orderDto){
		Order order = orderService.save(converter.fromDTO(orderDto));
		return new WrapperResponse<>(true, "succces", converter.fromEntity(order))
				.createResponse();
	}
	
	@DeleteMapping(value = "/orders/{id}")
	public ResponseEntity<?> delete(@PathVariable(name = "id") Long id){
		this.orderService.delete(id);
		return new WrapperResponse<>(true, "success", null).createResponse();
	}
}
