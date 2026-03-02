package com.ecommerce.notification.controller;
import com.ecommerce.notification.application.NotificationService;
import com.ecommerce.notification.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/notifications") @RequiredArgsConstructor
public class NotificationController {
  private final NotificationService notificationService;
  @PostMapping public ResponseEntity<NotificationResponse> send(@Valid @RequestBody NotificationRequest request){ return ResponseEntity.ok(notificationService.send(request)); }
  @GetMapping("/{messageId}") public ResponseEntity<NotificationResponse> get(@PathVariable String messageId){ return ResponseEntity.ok(notificationService.get(messageId)); }
}
