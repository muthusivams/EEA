package com.ecommerce.auth.domain;

import jakarta.persistence.*;
import java.time.Instant;
import lombok.*;

@Entity
@Table(name = "refresh_tokens")
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class RefreshToken {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long subjectId;
  @Column(nullable = false, unique = true)
  private String tokenHash;
  private boolean revoked;
  private Instant expiresAt;
  private Instant createdAt;
}
