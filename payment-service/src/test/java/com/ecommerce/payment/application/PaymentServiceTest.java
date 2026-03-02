package com.ecommerce.payment.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.ecommerce.payment.domain.PaymentTransaction;
import com.ecommerce.payment.dto.PaymentRequest;
import com.ecommerce.payment.dto.PaymentResponse;
import com.ecommerce.payment.mapper.PaymentMapper;
import com.ecommerce.payment.repository.PaymentRepository;
import io.micrometer.core.instrument.MeterRegistry;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {
  @Mock PaymentRepository repository; @Mock PaymentMapper mapper; @Mock MeterRegistry meterRegistry; @InjectMocks PaymentService service;
  @Test void shouldProcess(){
    PaymentTransaction tx=PaymentTransaction.builder().paymentReference("PAY-1").orderNumber("ORD-1").amount(BigDecimal.ONE).status("SUCCESS").build();
    when(repository.save(any(PaymentTransaction.class))).thenReturn(tx);
    when(mapper.toResponse(tx)).thenReturn(new PaymentResponse("PAY-1","ORD-1",BigDecimal.ONE,"SUCCESS"));
    assertEquals("SUCCESS", service.process(new PaymentRequest("ORD-1",BigDecimal.ONE)).status());
  }
  @Test void shouldGet(){
    PaymentTransaction tx=PaymentTransaction.builder().paymentReference("PAY-1").orderNumber("ORD-1").amount(BigDecimal.ONE).status("SUCCESS").build();
    when(repository.findByPaymentReference("PAY-1")).thenReturn(Optional.of(tx));
    when(mapper.toResponse(tx)).thenReturn(new PaymentResponse("PAY-1","ORD-1",BigDecimal.ONE,"SUCCESS"));
    assertEquals("PAY-1", service.get("PAY-1").paymentReference());
  }
}
