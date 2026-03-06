package com.ecommerce.auth.domain;

import jakarta.persistence.*;
import java.time.Instant;
import lombok.*;

@Entity
@Table(name = "users_credentials")
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class UserCredential {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false, unique = true)
  private String email;
  @Column(nullable = false)
  private String passwordHash;
  @Column(nullable = false)
  private String roles;
  private boolean locked;
  private int failedAttempts;
  private Instant createdAt;
}
