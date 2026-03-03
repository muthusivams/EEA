package com.ecommerce.order.domain;

import jakarta.persistence.*;
import java.time.Instant;
import lombok.*;

@Entity @Table(name="order_outbox")
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class OrderOutboxEvent {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  @Column(nullable=false) private String aggregateId;
  @Column(nullable=false) private String eventType;
  @Column(nullable=false, columnDefinition="TEXT") private String payload;
  @Column(nullable=false) private boolean published;
  private Instant createdAt;
}
