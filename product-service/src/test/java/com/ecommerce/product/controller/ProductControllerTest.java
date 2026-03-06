package com.ecommerce.product.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ecommerce.product.application.ProductService;
import com.ecommerce.product.dto.ProductRequest;
import com.ecommerce.product.dto.ProductResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;

@WebMvcTest(ProductController.class)
class ProductControllerTest {
  @Autowired MockMvc mockMvc;
  @Autowired ObjectMapper objectMapper;
  @MockBean ProductService productService;

  @WithMockUser(roles = "ADMIN")
  @Test
  void shouldCreateProduct() throws Exception {
    when(productService.create(any(ProductRequest.class)))
        .thenReturn(new ProductResponse(1L, "SKU-1", "Name", "Desc", BigDecimal.ONE, 10, true));

    mockMvc.perform(post("/products")
        .with(csrf())
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(new ProductRequest("SKU-1", "Name", "Desc", BigDecimal.ONE, 10, true))))
      .andExpect(status().isOk());
  }
}
