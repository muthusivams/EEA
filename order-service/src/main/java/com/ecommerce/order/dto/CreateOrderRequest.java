package com.ecommerce.order.dto;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
public record CreateOrderRequest(@NotBlank String userId, @DecimalMin("0.01") BigDecimal totalAmount) {}
