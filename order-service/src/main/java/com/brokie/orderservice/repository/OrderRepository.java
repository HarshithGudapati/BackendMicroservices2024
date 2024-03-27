package com.brokie.orderservice.repository;

import com.brokie.orderservice.modal.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {

}
