package com.ecommerce.cart.mapper;

import com.ecommerce.cart.domain.Cart;
import com.ecommerce.cart.dto.CartItemResponse;
import com.ecommerce.cart.dto.CartResponse;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class CartMapper {
  public CartResponse toResponse(Cart cart) {
    return new CartResponse(
        cart.getId(),
        cart.getUserId(),
        cart.getStatus(),
        cart.getItems().stream()
            .map(i -> new CartItemResponse(i.getProductSku(), i.getQuantity(), i.getUnitPrice()))
            .collect(Collectors.toList())
    );
  }
}
