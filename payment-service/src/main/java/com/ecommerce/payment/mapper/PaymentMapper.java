package com.ecommerce.payment.mapper;
import com.ecommerce.payment.domain.PaymentTransaction;
import com.ecommerce.payment.dto.PaymentResponse;
import org.springframework.stereotype.Component;
@Component
public class PaymentMapper { public PaymentResponse toResponse(PaymentTransaction p){ return new PaymentResponse(p.getPaymentReference(),p.getOrderNumber(),p.getAmount(),p.getStatus()); } }
