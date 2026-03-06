CREATE TABLE IF NOT EXISTS notification_messages (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  message_id VARCHAR(64) NOT NULL UNIQUE,
  channel VARCHAR(32) NOT NULL,
  recipient VARCHAR(255) NOT NULL,
  content TEXT NOT NULL,
  status VARCHAR(32) NOT NULL,
  created_at TIMESTAMP
);
CREATE INDEX idx_notification_recipient ON notification_messages(recipient);
