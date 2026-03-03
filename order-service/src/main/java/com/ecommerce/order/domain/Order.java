package com.ecommerce.order.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import lombok.*;

@Entity @Table(name="orders")
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class Order {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  @Column(nullable=false, unique=true) private String orderNumber;
  @Column(nullable=false) private String userId;
  @Column(nullable=false) private BigDecimal totalAmount;
  @Column(nullable=false) private String status;
  private Instant createdAt;
}
