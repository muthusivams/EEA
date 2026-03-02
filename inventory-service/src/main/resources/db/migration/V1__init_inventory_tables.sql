CREATE TABLE IF NOT EXISTS inventory_items (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  sku VARCHAR(64) NOT NULL UNIQUE,
  available_quantity INT NOT NULL DEFAULT 0,
  reserved_quantity INT NOT NULL DEFAULT 0,
  warehouse_code VARCHAR(64) NOT NULL,
  updated_at TIMESTAMP NULL
);

CREATE INDEX idx_inventory_sku ON inventory_items(sku);
