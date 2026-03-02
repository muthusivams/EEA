package com.ecommerce.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserProfileRequest(
    @NotBlank String externalAuthId,
    @NotBlank String firstName,
    @NotBlank String lastName,
    @Email String email,
    boolean marketingConsent
) {}
