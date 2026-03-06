package com.ecommerce.notification.mapper;
import com.ecommerce.notification.domain.NotificationMessage;
import com.ecommerce.notification.dto.NotificationResponse;
import org.springframework.stereotype.Component;
@Component
public class NotificationMapper { public NotificationResponse toResponse(NotificationMessage n){ return new NotificationResponse(n.getMessageId(),n.getChannel(),n.getRecipient(),n.getStatus()); } }
