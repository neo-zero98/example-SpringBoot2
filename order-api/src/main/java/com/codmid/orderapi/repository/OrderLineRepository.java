package com.codmid.orderapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codmid.orderapi.entity.OrderLine;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long>{

}
