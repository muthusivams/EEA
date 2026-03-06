package com.ecommerce.user.mapper;

import com.ecommerce.user.domain.UserProfile;
import com.ecommerce.user.dto.UserProfileRequest;
import com.ecommerce.user.dto.UserProfileResponse;
import java.time.Instant;
import org.springframework.stereotype.Component;

@Component
public class UserProfileMapper {
  public UserProfile toEntity(UserProfileRequest request) {
    return UserProfile.builder()
        .externalAuthId(request.externalAuthId())
        .firstName(request.firstName())
        .lastName(request.lastName())
        .email(request.email())
        .marketingConsent(request.marketingConsent())
        .createdAt(Instant.now())
        .build();
  }

  public UserProfileResponse toResponse(UserProfile profile) {
    return new UserProfileResponse(profile.getId(), profile.getExternalAuthId(), profile.getFirstName(), profile.getLastName(), profile.getEmail(), profile.isMarketingConsent());
  }
}
