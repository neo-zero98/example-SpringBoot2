package com.codmid.orderapi.config;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.codmid.orderapi.converters.OrderConverter;
import com.codmid.orderapi.converters.ProductConverter;
import com.codmid.orderapi.converters.UserConverter;

@Configuration
public class ConverterConfig {
	
	@Value("${config.datetimeFormat}")
	private String datetimeConverter; 

	@Bean
	public ProductConverter getProductConverter() {
		return new ProductConverter();
	}
	
	@Bean
	public OrderConverter getOrderConverter() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern(datetimeConverter);
		return new OrderConverter(format, getProductConverter(), getUserConverter());
	}
	
	@Bean
	public UserConverter getUserConverter() {
		return new UserConverter();
	}
}
