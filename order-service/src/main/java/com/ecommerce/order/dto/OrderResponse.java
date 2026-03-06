package com.ecommerce.order.dto;
import java.math.BigDecimal;
public record OrderResponse(String orderNumber, String userId, BigDecimal totalAmount, String status) {}
