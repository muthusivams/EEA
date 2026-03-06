package com.ecommerce.order.mapper;
import com.ecommerce.order.domain.Order;
import com.ecommerce.order.dto.OrderResponse;
import org.springframework.stereotype.Component;
@Component
public class OrderMapper { public OrderResponse toResponse(Order o){ return new OrderResponse(o.getOrderNumber(),o.getUserId(),o.getTotalAmount(),o.getStatus()); } }
