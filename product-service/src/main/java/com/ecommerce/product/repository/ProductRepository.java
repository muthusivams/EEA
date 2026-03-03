package com.ecommerce.product.repository;

import com.ecommerce.product.domain.Product;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
  Optional<Product> findBySkuAndActiveTrue(String sku);
}
