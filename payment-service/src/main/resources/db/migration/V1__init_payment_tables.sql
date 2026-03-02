CREATE TABLE IF NOT EXISTS payment_transactions (
  id BIGSERIAL PRIMARY KEY,
  payment_reference VARCHAR(64) NOT NULL UNIQUE,
  order_number VARCHAR(64) NOT NULL,
  amount NUMERIC(12,2) NOT NULL,
  status VARCHAR(32) NOT NULL,
  created_at TIMESTAMP
);
CREATE INDEX IF NOT EXISTS idx_payment_order ON payment_transactions(order_number);
