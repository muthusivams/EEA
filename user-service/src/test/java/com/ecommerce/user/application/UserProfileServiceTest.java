package com.ecommerce.user.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.ecommerce.user.domain.UserProfile;
import com.ecommerce.user.dto.UserProfileRequest;
import com.ecommerce.user.dto.UserProfileResponse;
import com.ecommerce.user.mapper.UserProfileMapper;
import com.ecommerce.user.repository.UserProfileRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserProfileServiceTest {
  @Mock UserProfileRepository repository;
  @Mock UserProfileMapper mapper;
  @InjectMocks UserProfileService service;

  @Test
  void shouldGetProfileByExternalId() {
    UserProfile profile = UserProfile.builder().id(10L).externalAuthId("auth-1").email("x@y.com").build();
    when(repository.findByExternalAuthId("auth-1")).thenReturn(Optional.of(profile));
    when(mapper.toResponse(profile)).thenReturn(new UserProfileResponse(10L, "auth-1", "A", "B", "x@y.com", false));

    UserProfileResponse response = service.getByExternalAuthId("auth-1");

    assertEquals("auth-1", response.externalAuthId());
  }
}
