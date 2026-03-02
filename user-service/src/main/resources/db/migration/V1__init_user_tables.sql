CREATE TABLE IF NOT EXISTS user_profiles (
  id BIGSERIAL PRIMARY KEY,
  external_auth_id VARCHAR(128) NOT NULL UNIQUE,
  first_name VARCHAR(120),
  last_name VARCHAR(120),
  email VARCHAR(255) NOT NULL UNIQUE,
  marketing_consent BOOLEAN DEFAULT FALSE,
  created_at TIMESTAMP
);
