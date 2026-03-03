package com.ecommerce.order.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.ecommerce.order.domain.Order;
import com.ecommerce.order.dto.CreateOrderRequest;
import com.ecommerce.order.dto.OrderResponse;
import com.ecommerce.order.mapper.OrderMapper;
import com.ecommerce.order.repository.OrderOutboxRepository;
import com.ecommerce.order.repository.OrderRepository;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
  @Mock OrderRepository orderRepository;
  @Mock OrderOutboxRepository outboxRepository;
  @Mock OrderMapper mapper;
  @Mock MeterRegistry meterRegistry;
  @Mock Counter counter;
  @InjectMocks OrderService service;

  @Test void shouldCreateOrder(){
    Order order = Order.builder().orderNumber("ORD-1").userId("u1").totalAmount(BigDecimal.TEN).status("PENDING").build();
    when(orderRepository.save(any(Order.class))).thenReturn(order);
    when(mapper.toResponse(order)).thenReturn(new OrderResponse("ORD-1","u1",BigDecimal.TEN,"PENDING"));
    when(meterRegistry.counter("orders_created_total")).thenReturn(counter);
    assertEquals("PENDING", service.create(new CreateOrderRequest("u1",BigDecimal.TEN)).status());
    verify(outboxRepository).save(any()); verify(counter).increment();
  }

  @Test void shouldGetOrder(){
    Order o=Order.builder().orderNumber("ORD-X").userId("u").totalAmount(BigDecimal.ONE).status("PENDING").build();
    when(orderRepository.findByOrderNumber("ORD-X")).thenReturn(Optional.of(o));
    when(mapper.toResponse(o)).thenReturn(new OrderResponse("ORD-X","u",BigDecimal.ONE,"PENDING"));
    assertEquals("ORD-X", service.getByOrderNumber("ORD-X").orderNumber());
  }
}
