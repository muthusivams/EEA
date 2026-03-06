package com.ecommerce.user.controller;

import com.ecommerce.user.application.UserProfileService;
import com.ecommerce.user.dto.UserProfileRequest;
import com.ecommerce.user.dto.UserProfileResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserProfileController {

  private final UserProfileService userProfileService;

  @PostMapping
  public ResponseEntity<UserProfileResponse> create(@Valid @RequestBody UserProfileRequest request) {
    return ResponseEntity.ok(userProfileService.create(request));
  }

  @GetMapping("/{externalAuthId}")
  public ResponseEntity<UserProfileResponse> getByExternalAuthId(@PathVariable String externalAuthId) {
    return ResponseEntity.ok(userProfileService.getByExternalAuthId(externalAuthId));
  }
}
