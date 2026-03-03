CREATE TABLE IF NOT EXISTS users_credentials (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  email VARCHAR(255) NOT NULL UNIQUE,
  password_hash VARCHAR(255) NOT NULL,
  roles VARCHAR(255) NOT NULL,
  locked BOOLEAN DEFAULT FALSE,
  failed_attempts INT DEFAULT 0,
  created_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS refresh_tokens (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  subject_id BIGINT NOT NULL,
  token_hash VARCHAR(255) NOT NULL UNIQUE,
  revoked BOOLEAN DEFAULT FALSE,
  expires_at TIMESTAMP,
  created_at TIMESTAMP
);

CREATE INDEX idx_refresh_subject_revoked ON refresh_tokens(subject_id, revoked);
