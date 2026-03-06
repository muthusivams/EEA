package com.ecommerce.notification.application;

import com.ecommerce.notification.domain.NotificationMessage;
import com.ecommerce.notification.dto.NotificationRequest;
import com.ecommerce.notification.dto.NotificationResponse;
import com.ecommerce.notification.exception.NotFoundException;
import com.ecommerce.notification.mapper.NotificationMapper;
import com.ecommerce.notification.repository.NotificationRepository;
import io.micrometer.core.instrument.MeterRegistry;
import java.time.Instant;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j @Service @RequiredArgsConstructor
public class NotificationService {
  private final NotificationRepository repository;
  private final NotificationMapper mapper;
  private final MeterRegistry meterRegistry;

  public NotificationResponse send(NotificationRequest request){
    String id="NTF-"+UUID.randomUUID().toString().substring(0,8);
    NotificationMessage msg = repository.save(NotificationMessage.builder().messageId(id).channel(request.channel()).recipient(request.recipient()).content(request.content()).status("SENT").createdAt(Instant.now()).build());
    meterRegistry.counter("notifications_sent_total", "channel", request.channel()).increment();
    log.info("Notification sent id={} channel={} recipient={}", id, request.channel(), request.recipient());
    return mapper.toResponse(msg);
  }

  public NotificationResponse get(String messageId){
    return repository.findByMessageId(messageId).map(mapper::toResponse).orElseThrow(()->new NotFoundException("Notification not found"));
  }
}
