package com.ecommerce.product.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import lombok.*;

@Entity
@Table(name = "products")
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class Product {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false, unique = true)
  private String sku;
  @Column(nullable = false)
  private String name;
  private String description;
  @Column(nullable = false)
  private BigDecimal price;
  @Column(nullable = false)
  private Integer stockQuantity;
  @Column(nullable = false)
  private boolean active;
  private Instant createdAt;
  private Instant updatedAt;
}
