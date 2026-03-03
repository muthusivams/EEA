package com.ecommerce.cart.repository;

import com.ecommerce.cart.domain.Cart;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
  Optional<Cart> findByUserIdAndStatus(String userId, String status);
}
