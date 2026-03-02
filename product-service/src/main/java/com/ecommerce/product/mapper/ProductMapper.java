package com.ecommerce.product.mapper;

import com.ecommerce.product.domain.Product;
import com.ecommerce.product.dto.ProductRequest;
import com.ecommerce.product.dto.ProductResponse;
import java.time.Instant;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
  public Product toEntity(ProductRequest request) {
    return Product.builder()
        .sku(request.sku())
        .name(request.name())
        .description(request.description())
        .price(request.price())
        .stockQuantity(request.stockQuantity())
        .active(request.active())
        .createdAt(Instant.now())
        .updatedAt(Instant.now())
        .build();
  }

  public ProductResponse toResponse(Product product) {
    return new ProductResponse(product.getId(), product.getSku(), product.getName(), product.getDescription(),
        product.getPrice(), product.getStockQuantity(), product.isActive());
  }
}
