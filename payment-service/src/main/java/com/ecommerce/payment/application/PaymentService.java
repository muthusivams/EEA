package com.ecommerce.payment.application;

import com.ecommerce.payment.domain.PaymentTransaction;
import com.ecommerce.payment.dto.PaymentRequest;
import com.ecommerce.payment.dto.PaymentResponse;
import com.ecommerce.payment.exception.NotFoundException;
import com.ecommerce.payment.mapper.PaymentMapper;
import com.ecommerce.payment.repository.PaymentRepository;
import io.micrometer.core.instrument.MeterRegistry;
import java.time.Instant;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j @Service @RequiredArgsConstructor
public class PaymentService {
  private final PaymentRepository repository;
  private final PaymentMapper mapper;
  private final MeterRegistry meterRegistry;

  public PaymentResponse process(PaymentRequest request){
    String ref="PAY-"+UUID.randomUUID().toString().substring(0,8);
    PaymentTransaction tx = repository.save(PaymentTransaction.builder().paymentReference(ref).orderNumber(request.orderNumber()).amount(request.amount()).status("SUCCESS").createdAt(Instant.now()).build());
    meterRegistry.counter("failed_payments_total", "status", "success").increment(0);
    log.info("Payment processed ref={} order={}", ref, request.orderNumber());
    return mapper.toResponse(tx);
  }

  public PaymentResponse get(String paymentReference){
    return repository.findByPaymentReference(paymentReference).map(mapper::toResponse).orElseThrow(()->new NotFoundException("Payment not found"));
  }
}
