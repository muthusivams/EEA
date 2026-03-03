package com.ecommerce.auth.application;

import com.ecommerce.auth.domain.RefreshToken;
import com.ecommerce.auth.domain.UserCredential;
import com.ecommerce.auth.dto.LoginRequest;
import com.ecommerce.auth.dto.TokenResponse;
import com.ecommerce.auth.exception.UnauthorizedException;
import com.ecommerce.auth.repository.RefreshTokenRepository;
import com.ecommerce.auth.repository.UserCredentialRepository;
import com.ecommerce.auth.security.JwtProvider;
import com.ecommerce.auth.util.HashingUtil;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserCredentialRepository userRepository;
  private final RefreshTokenRepository refreshTokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtProvider jwtProvider;

  public TokenResponse login(LoginRequest request) {
    UserCredential user = userRepository.findByEmail(request.email())
        .orElseThrow(() -> new UnauthorizedException("Invalid credentials"));

    if (user.isLocked() || !passwordEncoder.matches(request.password(), user.getPasswordHash())) {
      log.warn("Failed auth attempt for {}", request.email());
      throw new UnauthorizedException("Invalid credentials");
    }

    String accessToken = jwtProvider.generateAccessToken(user.getId(), user.getRoles());
    String refreshToken = jwtProvider.generateRefreshToken(user.getId());

    refreshTokenRepository.save(RefreshToken.builder()
        .subjectId(user.getId())
        .tokenHash(HashingUtil.sha256(refreshToken))
        .revoked(false)
        .createdAt(Instant.now())
        .expiresAt(Instant.now().plusSeconds(604800))
        .build());

    return new TokenResponse(accessToken, refreshToken, "Bearer", 900);
  }
}
