package com.codmid.orderapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codmid.orderapi.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
