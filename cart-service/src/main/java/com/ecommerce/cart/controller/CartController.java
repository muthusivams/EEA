package com.ecommerce.cart.controller;

import com.ecommerce.cart.application.CartService;
import com.ecommerce.cart.dto.AddItemRequest;
import com.ecommerce.cart.dto.CartResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {

  private final CartService cartService;

  @PostMapping("/{userId}/items")
  public ResponseEntity<CartResponse> addItem(@PathVariable("userId") String userId, @Valid @RequestBody AddItemRequest request) {
    return ResponseEntity.ok(cartService.addItem(userId, request));
  }

  @GetMapping("/{userId}")
  public ResponseEntity<CartResponse> getActiveCart(@PathVariable("userId") String userId) {
    return ResponseEntity.ok(cartService.getActiveCart(userId));
  }
}
