package com.ecommerce.order.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ecommerce.order.application.OrderService;
import com.ecommerce.order.dto.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(OrderController.class)
class OrderControllerTest {
  @Autowired MockMvc mockMvc; @Autowired ObjectMapper objectMapper; @MockBean OrderService orderService;
  @Test void shouldCreate() throws Exception {
    when(orderService.create(any(CreateOrderRequest.class))).thenReturn(new OrderResponse("ORD-1","u1",BigDecimal.ONE,"PENDING"));
    mockMvc.perform(post("/orders").contentType(MediaType.APPLICATION_JSON)
      .content(objectMapper.writeValueAsString(new CreateOrderRequest("u1",BigDecimal.ONE))))
      .andExpect(status().isOk());
  }
}
