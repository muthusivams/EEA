package com.ecommerce.order.repository;
import com.ecommerce.order.domain.OrderOutboxEvent;
import org.springframework.data.jpa.repository.JpaRepository;
public interface OrderOutboxRepository extends JpaRepository<OrderOutboxEvent,Long>{}
