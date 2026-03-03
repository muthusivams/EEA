package com.ecommerce.payment.controller;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.ecommerce.payment.application.PaymentService;
import com.ecommerce.payment.dto.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
@WebMvcTest(PaymentController.class)
class PaymentControllerTest {
  @Autowired MockMvc mockMvc; @Autowired ObjectMapper objectMapper; @MockBean PaymentService paymentService;
  @Test void shouldProcess() throws Exception {
    when(paymentService.process(any(PaymentRequest.class))).thenReturn(new PaymentResponse("PAY-1","ORD-1",BigDecimal.ONE,"SUCCESS"));
    mockMvc.perform(post("/payments").with(csrf()).contentType(MediaType.APPLICATION_JSON)
      .content(objectMapper.writeValueAsString(new PaymentRequest("ORD-1",BigDecimal.ONE))))
      .andExpect(status().isOk());
  }
}
