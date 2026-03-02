CREATE TABLE IF NOT EXISTS orders (
  id BIGSERIAL PRIMARY KEY,
  order_number VARCHAR(64) NOT NULL UNIQUE,
  user_id VARCHAR(128) NOT NULL,
  total_amount NUMERIC(12,2) NOT NULL,
  status VARCHAR(32) NOT NULL,
  created_at TIMESTAMP
);
CREATE TABLE IF NOT EXISTS order_outbox (
  id BIGSERIAL PRIMARY KEY,
  aggregate_id VARCHAR(64) NOT NULL,
  event_type VARCHAR(64) NOT NULL,
  payload TEXT NOT NULL,
  published BOOLEAN NOT NULL DEFAULT FALSE,
  created_at TIMESTAMP
);
CREATE INDEX IF NOT EXISTS idx_orders_user_status ON orders(user_id,status);
