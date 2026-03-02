package com.ecommerce.cart.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.*;

@Entity
@Table(name = "cart_items")
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class CartItem {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cart_id", nullable = false)
  private Cart cart;
  @Column(nullable = false)
  private String productSku;
  @Column(nullable = false)
  private Integer quantity;
  @Column(nullable = false)
  private BigDecimal unitPrice;
}
