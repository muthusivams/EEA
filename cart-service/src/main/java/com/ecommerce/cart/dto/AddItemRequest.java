package com.ecommerce.cart.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record AddItemRequest(
    @NotBlank String productSku,
    @Min(1) Integer quantity,
    @DecimalMin("0.01") BigDecimal unitPrice
) {}
