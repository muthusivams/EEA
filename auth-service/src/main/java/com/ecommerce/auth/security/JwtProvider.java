package com.ecommerce.auth.security;

import java.time.Instant;
import java.util.Base64;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {
  public String generateAccessToken(Long userId, String roles) {
    String payload = userId + ":" + roles + ":" + Instant.now().plusSeconds(900).toEpochMilli();
    return Base64.getEncoder().encodeToString(payload.getBytes());
  }
  public String generateRefreshToken(Long userId) {
    String payload = "refresh:" + userId + ":" + Instant.now().toEpochMilli();
    return Base64.getEncoder().encodeToString(payload.getBytes());
  }
}
