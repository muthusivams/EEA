package com.ecommerce.cart.dto;

import java.math.BigDecimal;

public record CartItemResponse(String productSku, Integer quantity, BigDecimal unitPrice) {}
