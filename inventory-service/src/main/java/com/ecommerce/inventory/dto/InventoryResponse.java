package com.ecommerce.inventory.dto;
public record InventoryResponse(String sku, Integer availableQuantity, Integer reservedQuantity, String warehouseCode) {}
