package com.ecommerce.order.application;

import com.ecommerce.order.domain.Order;
import com.ecommerce.order.domain.OrderOutboxEvent;
import com.ecommerce.order.dto.CreateOrderRequest;
import com.ecommerce.order.dto.OrderResponse;
import com.ecommerce.order.exception.NotFoundException;
import com.ecommerce.order.mapper.OrderMapper;
import com.ecommerce.order.repository.OrderOutboxRepository;
import com.ecommerce.order.repository.OrderRepository;
import io.micrometer.core.instrument.MeterRegistry;
import java.time.Instant;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j @Service @RequiredArgsConstructor
public class OrderService {
  private final OrderRepository orderRepository;
  private final OrderOutboxRepository outboxRepository;
  private final OrderMapper mapper;
  private final MeterRegistry meterRegistry;

  public OrderResponse create(CreateOrderRequest request){
    String orderNumber = "ORD-"+UUID.randomUUID().toString().substring(0,8);
    Order order = orderRepository.save(Order.builder().orderNumber(orderNumber).userId(request.userId()).totalAmount(request.totalAmount()).status("PENDING").createdAt(Instant.now()).build());
    outboxRepository.save(OrderOutboxEvent.builder().aggregateId(orderNumber).eventType("OrderCreated").payload("{\"orderNumber\":\""+orderNumber+"\"}").published(false).createdAt(Instant.now()).build());
    meterRegistry.counter("orders_created_total").increment();
    log.info("Order created orderNumber={} userId={}",orderNumber,request.userId());
    return mapper.toResponse(order);
  }

  public OrderResponse getByOrderNumber(String orderNumber){
    return orderRepository.findByOrderNumber(orderNumber).map(mapper::toResponse).orElseThrow(()->new NotFoundException("Order not found"));
  }
}
