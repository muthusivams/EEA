package com.ecommerce.auth.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.ecommerce.auth.domain.UserCredential;
import com.ecommerce.auth.dto.LoginRequest;
import com.ecommerce.auth.dto.TokenResponse;
import com.ecommerce.auth.repository.RefreshTokenRepository;
import com.ecommerce.auth.repository.UserCredentialRepository;
import com.ecommerce.auth.security.JwtProvider;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {
  @Mock UserCredentialRepository userRepository;
  @Mock RefreshTokenRepository refreshTokenRepository;
  @Mock PasswordEncoder passwordEncoder;
  @Mock JwtProvider jwtProvider;
  @InjectMocks AuthService authService;

  @Test
  void shouldLoginSuccessfully() {
    UserCredential user = UserCredential.builder().id(1L).email("a@b.com").passwordHash("hash").roles("ROLE_USER").build();
    when(userRepository.findByEmail("a@b.com")).thenReturn(Optional.of(user));
    when(passwordEncoder.matches("pass", "hash")).thenReturn(true);
    when(jwtProvider.generateAccessToken(1L, "ROLE_USER")).thenReturn("access");
    when(jwtProvider.generateRefreshToken(1L)).thenReturn("refresh");

    TokenResponse response = authService.login(new LoginRequest("a@b.com", "pass"));

    assertEquals("access", response.accessToken());
    verify(refreshTokenRepository).save(any());
  }
}
