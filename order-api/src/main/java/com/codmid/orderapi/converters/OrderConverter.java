package com.codmid.orderapi.converters;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import com.codmid.orderapi.dtos.OrderDTO;
import com.codmid.orderapi.dtos.OrderLineDTO;
import com.codmid.orderapi.entity.Order;
import com.codmid.orderapi.entity.OrderLine;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OrderConverter extends AbstractConverter<Order, OrderDTO>{
	//private static final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
	//private ProductConverter productConverter = new ProductConverter();
	private DateTimeFormatter dateTimeFormat;
	private ProductConverter productConverter;
	private UserConverter userConverter;
	
	@Override
	public OrderDTO fromEntity(Order entity) {
		if(entity == null) return null;
		List<OrderLineDTO> lines = fromOrderLineEntity(entity.getLines());
		
		return OrderDTO.builder()
				.id(entity.getId())
				.lines(lines)
				.regDate(entity.getRegDate().format(dateTimeFormat))
				.total(entity.getTotal())
				.user(this.userConverter.fromEntity(entity.getUser()))
				.build();
	}

	@Override
	public Order fromDTO(OrderDTO dto) {
		if(dto == null) return null;
		List<OrderLine> lines = fromOrderLineDTO(dto.getLines());
		
		return Order.builder()
				.id(dto.getId())
				.lines(lines)
				.total(dto.getTotal())
				.user(this.userConverter.fromDTO(dto.getUser()))
				.build();
	}
	
	private List<OrderLineDTO> fromOrderLineEntity(List<OrderLine> lines) {
		if(lines == null) return null;
		return lines.stream().map(line -> {
			return OrderLineDTO.builder()
					.id(line.getId())
					//.order(line.getOrder())
					.product(this.productConverter.fromEntity(line.getProduct()))
					.price(line.getPrice())
					.quantity(line.getQuantity())
					.total(line.getTotal())
					.build();
		}).collect(Collectors.toList());
	}
	
	private List<OrderLine> fromOrderLineDTO(List<OrderLineDTO> lines) {
		if(lines == null) return null;
		return lines.stream().map(line -> {
			return OrderLine.builder()
					.id(line.getId())
					//.order(line.getOrder())
					.product(this.productConverter.fromDTO(line.getProduct()))
					.price(line.getPrice())
					.quantity(line.getQuantity())
					.total(line.getTotal())
					.build();
		}).collect(Collectors.toList());
	}

}
