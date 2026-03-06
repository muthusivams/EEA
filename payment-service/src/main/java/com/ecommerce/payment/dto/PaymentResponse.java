package com.ecommerce.payment.dto;
import java.math.BigDecimal;
public record PaymentResponse(String paymentReference, String orderNumber, BigDecimal amount, String status) {}
