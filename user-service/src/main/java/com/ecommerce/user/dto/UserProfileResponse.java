package com.ecommerce.user.dto;

public record UserProfileResponse(Long id, String externalAuthId, String firstName, String lastName, String email, boolean marketingConsent) {}
