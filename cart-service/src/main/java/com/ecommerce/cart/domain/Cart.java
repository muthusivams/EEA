package com.ecommerce.cart.domain;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

@Entity
@Table(name = "carts")
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class Cart {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false, unique = true)
  private String userId;
  @Column(nullable = false)
  private String status;
  private Instant createdAt;
  private Instant updatedAt;

  @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
  @Builder.Default
  private List<CartItem> items = new ArrayList<>();
}
