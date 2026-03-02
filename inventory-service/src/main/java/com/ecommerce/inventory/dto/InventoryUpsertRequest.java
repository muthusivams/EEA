package com.ecommerce.inventory.dto;
import jakarta.validation.constraints.*;
public record InventoryUpsertRequest(@NotBlank String sku, @Min(0) Integer availableQuantity, @Min(0) Integer reservedQuantity, @NotBlank String warehouseCode) {}
