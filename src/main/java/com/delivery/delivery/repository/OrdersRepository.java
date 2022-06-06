package com.delivery.delivery.repository;

import com.delivery.delivery.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Order, Long> {
}
