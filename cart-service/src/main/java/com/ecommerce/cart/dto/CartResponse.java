package com.ecommerce.cart.dto;

import java.util.List;

public record CartResponse(Long id, String userId, String status, List<CartItemResponse> items) {}
