package com.ecommerce.payment.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import lombok.*;

@Entity @Table(name="payment_transactions")
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class PaymentTransaction {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  @Column(nullable=false, unique=true) private String paymentReference;
  @Column(nullable=false) private String orderNumber;
  @Column(nullable=false) private BigDecimal amount;
  @Column(nullable=false) private String status;
  private Instant createdAt;
}
