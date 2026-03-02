package com.ecommerce.notification.repository;
import com.ecommerce.notification.domain.NotificationMessage;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
public interface NotificationRepository extends JpaRepository<NotificationMessage,Long>{ Optional<NotificationMessage> findByMessageId(String messageId); }
