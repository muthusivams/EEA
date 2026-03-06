package com.ecommerce.payment.repository;
import com.ecommerce.payment.domain.PaymentTransaction;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PaymentRepository extends JpaRepository<PaymentTransaction,Long>{ Optional<PaymentTransaction> findByPaymentReference(String paymentReference); }
