package com.ecommerce.cart.application;

import com.ecommerce.cart.domain.Cart;
import com.ecommerce.cart.domain.CartItem;
import com.ecommerce.cart.dto.AddItemRequest;
import com.ecommerce.cart.dto.CartResponse;
import com.ecommerce.cart.mapper.CartMapper;
import com.ecommerce.cart.repository.CartRepository;
import io.micrometer.core.instrument.MeterRegistry;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartService {

  private static final String ACTIVE = "ACTIVE";
  private final CartRepository cartRepository;
  private final CartMapper cartMapper;
  private final MeterRegistry meterRegistry;

  public CartResponse addItem(String userId, AddItemRequest request) {
    Cart cart = cartRepository.findByUserIdAndStatus(userId, ACTIVE)
        .orElseGet(() -> cartRepository.save(Cart.builder()
            .userId(userId)
            .status(ACTIVE)
            .createdAt(Instant.now())
            .updatedAt(Instant.now())
            .build()));

    CartItem item = CartItem.builder()
        .cart(cart)
        .productSku(request.productSku())
        .quantity(request.quantity())
        .unitPrice(request.unitPrice())
        .build();

    cart.getItems().add(item);
    cart.setUpdatedAt(Instant.now());
    Cart updated = cartRepository.save(cart);

    meterRegistry.counter("cart_items_added_total").increment();
    log.info("Item added to cart userId={} sku={} qty={}", userId, request.productSku(), request.quantity());
    return cartMapper.toResponse(updated);
  }

  public CartResponse getActiveCart(String userId) {
    Cart cart = cartRepository.findByUserIdAndStatus(userId, ACTIVE)
        .orElseGet(() -> Cart.builder().id(0L).userId(userId).status(ACTIVE).build());
    return cartMapper.toResponse(cart);
  }
}
