package com.ecommerce.notification.controller;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.ecommerce.notification.application.NotificationService;
import com.ecommerce.notification.dto.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
@WebMvcTest(NotificationController.class)
class NotificationControllerTest {
  @Autowired MockMvc mockMvc; @Autowired ObjectMapper objectMapper; @MockBean NotificationService notificationService;
  @Test void shouldSend() throws Exception {
    when(notificationService.send(any(NotificationRequest.class))).thenReturn(new NotificationResponse("NTF-1","EMAIL","a@b.com","SENT"));
    mockMvc.perform(post("/notifications").contentType(MediaType.APPLICATION_JSON)
      .content(objectMapper.writeValueAsString(new NotificationRequest("EMAIL","a@b.com","hi"))))
      .andExpect(status().isOk());
  }
}
