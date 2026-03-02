package com.ecommerce.order.repository;
import com.ecommerce.order.domain.Order;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
public interface OrderRepository extends JpaRepository<Order,Long>{ Optional<Order> findByOrderNumber(String orderNumber); }
