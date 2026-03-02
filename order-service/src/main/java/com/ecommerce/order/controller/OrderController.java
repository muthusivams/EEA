package com.ecommerce.order.controller;
import com.ecommerce.order.application.OrderService;
import com.ecommerce.order.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/orders") @RequiredArgsConstructor
public class OrderController {
  private final OrderService orderService;
  @PostMapping public ResponseEntity<OrderResponse> create(@Valid @RequestBody CreateOrderRequest request){ return ResponseEntity.ok(orderService.create(request)); }
  @GetMapping("/{orderNumber}") public ResponseEntity<OrderResponse> get(@PathVariable String orderNumber){ return ResponseEntity.ok(orderService.getByOrderNumber(orderNumber)); }
}
