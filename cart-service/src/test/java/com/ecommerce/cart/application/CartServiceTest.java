package com.ecommerce.cart.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.ecommerce.cart.domain.Cart;
import com.ecommerce.cart.dto.AddItemRequest;
import com.ecommerce.cart.dto.CartResponse;
import com.ecommerce.cart.mapper.CartMapper;
import com.ecommerce.cart.repository.CartRepository;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {
  @Mock CartRepository cartRepository;
  @Mock CartMapper cartMapper;
  @Mock MeterRegistry meterRegistry;
  @Mock Counter counter;
  @InjectMocks CartService cartService;

  @Test
  void shouldAddItem() {
    Cart cart = Cart.builder().id(1L).userId("u1").status("ACTIVE").build();
    when(cartRepository.findByUserIdAndStatus("u1", "ACTIVE")).thenReturn(Optional.of(cart));
    when(cartRepository.save(any(Cart.class))).thenReturn(cart);
    when(cartMapper.toResponse(cart)).thenReturn(new CartResponse(1L, "u1", "ACTIVE", java.util.List.of()));
    when(meterRegistry.counter("cart_items_added_total")).thenReturn(counter);

    CartResponse response = cartService.addItem("u1", new AddItemRequest("SKU1", 1, BigDecimal.ONE));

    assertEquals("u1", response.userId());
    verify(counter).increment();
  }
}
