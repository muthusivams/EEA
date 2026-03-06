package com.ecommerce.payment.controller;
import com.ecommerce.payment.application.PaymentService;
import com.ecommerce.payment.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/payments") @RequiredArgsConstructor
public class PaymentController {
  private final PaymentService paymentService;
  @PostMapping public ResponseEntity<PaymentResponse> process(@Valid @RequestBody PaymentRequest request){ return ResponseEntity.ok(paymentService.process(request)); }
  @GetMapping("/{paymentReference}") public ResponseEntity<PaymentResponse> get(@PathVariable String paymentReference){ return ResponseEntity.ok(paymentService.get(paymentReference)); }
}
