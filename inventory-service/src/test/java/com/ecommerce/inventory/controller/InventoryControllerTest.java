package com.ecommerce.inventory.controller;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.ecommerce.inventory.application.InventoryService;
import com.ecommerce.inventory.dto.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;
@WebMvcTest(InventoryController.class)
class InventoryControllerTest {
  @Autowired MockMvc mockMvc; @Autowired ObjectMapper objectMapper; @MockBean InventoryService inventoryService;
  @WithMockUser(roles = "ADMIN")
  @Test void shouldUpsert() throws Exception {
    when(inventoryService.upsert(any(InventoryUpsertRequest.class))).thenReturn(new InventoryResponse("SKU-1",10,2,"W1"));
    mockMvc.perform(post("/inventory").with(csrf()).contentType(MediaType.APPLICATION_JSON)
      .content(objectMapper.writeValueAsString(new InventoryUpsertRequest("SKU-1",10,2,"W1"))))
      .andExpect(status().isOk());
  }
}
