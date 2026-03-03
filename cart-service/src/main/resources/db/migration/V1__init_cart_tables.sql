CREATE TABLE IF NOT EXISTS carts (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id VARCHAR(128) NOT NULL,
  status VARCHAR(32) NOT NULL,
  created_at TIMESTAMP,
  updated_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS cart_items (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  cart_id BIGINT NOT NULL REFERENCES carts(id) ON DELETE CASCADE,
  product_sku VARCHAR(64) NOT NULL,
  quantity INT NOT NULL,
  unit_price NUMERIC(12,2) NOT NULL
);

CREATE INDEX idx_carts_user_status ON carts(user_id, status);
