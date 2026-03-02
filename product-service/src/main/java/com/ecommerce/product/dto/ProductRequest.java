package com.ecommerce.product.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record ProductRequest(
    @NotBlank String sku,
    @NotBlank String name,
    String description,
    @DecimalMin("0.01") BigDecimal price,
    @Min(0) Integer stockQuantity,
    boolean active
) {}
