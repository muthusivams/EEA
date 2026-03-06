package com.ecommerce.user.domain;

import jakarta.persistence.*;
import java.time.Instant;
import lombok.*;

@Entity
@Table(name = "user_profiles")
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class UserProfile {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false, unique = true)
  private String externalAuthId;
  private String firstName;
  private String lastName;
  @Column(nullable = false, unique = true)
  private String email;
  private boolean marketingConsent;
  private Instant createdAt;
}
