package com.ecommerce.notification.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.ecommerce.notification.domain.NotificationMessage;
import com.ecommerce.notification.dto.NotificationRequest;
import com.ecommerce.notification.dto.NotificationResponse;
import com.ecommerce.notification.mapper.NotificationMapper;
import com.ecommerce.notification.repository.NotificationRepository;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {
  @Mock NotificationRepository repository; @Mock NotificationMapper mapper; @Mock MeterRegistry meterRegistry; @Mock Counter counter; @InjectMocks NotificationService service;
  @Test void shouldSend(){
    NotificationMessage m=NotificationMessage.builder().messageId("NTF-1").channel("EMAIL").recipient("a@b.com").status("SENT").build();
    when(repository.save(any(NotificationMessage.class))).thenReturn(m);
    when(mapper.toResponse(m)).thenReturn(new NotificationResponse("NTF-1","EMAIL","a@b.com","SENT"));
    when(meterRegistry.counter("notifications_sent_total", "channel", "EMAIL")).thenReturn(counter);
    assertEquals("SENT", service.send(new NotificationRequest("EMAIL","a@b.com","hello")).status());
  }
  @Test void shouldGet(){
    NotificationMessage m=NotificationMessage.builder().messageId("NTF-1").channel("EMAIL").recipient("a@b.com").status("SENT").build();
    when(repository.findByMessageId("NTF-1")).thenReturn(Optional.of(m));
    when(mapper.toResponse(m)).thenReturn(new NotificationResponse("NTF-1","EMAIL","a@b.com","SENT"));
    assertEquals("NTF-1", service.get("NTF-1").messageId());
  }
}
