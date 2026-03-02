package com.ecommerce.user.application;

import com.ecommerce.user.domain.UserProfile;
import com.ecommerce.user.dto.UserProfileRequest;
import com.ecommerce.user.dto.UserProfileResponse;
import com.ecommerce.user.exception.NotFoundException;
import com.ecommerce.user.mapper.UserProfileMapper;
import com.ecommerce.user.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserProfileService {

  private final UserProfileRepository repository;
  private final UserProfileMapper mapper;

  public UserProfileResponse create(UserProfileRequest request) {
    UserProfile profile = repository.save(mapper.toEntity(request));
    log.info("Profile created for externalAuthId={}", request.externalAuthId());
    return mapper.toResponse(profile);
  }

  public UserProfileResponse getByExternalAuthId(String externalAuthId) {
    return repository.findByExternalAuthId(externalAuthId)
        .map(mapper::toResponse)
        .orElseThrow(() -> new NotFoundException("Profile not found"));
  }
}
