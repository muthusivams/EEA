package com.ecommerce.payment.dto;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
public record PaymentRequest(@NotBlank String orderNumber, @DecimalMin("0.01") BigDecimal amount) {}
