package com.ecommerce.notification.dto;
import jakarta.validation.constraints.*;
public record NotificationRequest(@NotBlank String channel, @NotBlank String recipient, @NotBlank String content) {}
