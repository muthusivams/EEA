package com.ecommerce.notification.domain;

import jakarta.persistence.*;
import java.time.Instant;
import lombok.*;

@Entity @Table(name="notification_messages")
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class NotificationMessage {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  @Column(nullable=false, unique=true) private String messageId;
  @Column(nullable=false) private String channel;
  @Column(nullable=false) private String recipient;
  @Column(nullable=false, columnDefinition="TEXT") private String content;
  @Column(nullable=false) private String status;
  private Instant createdAt;
}
