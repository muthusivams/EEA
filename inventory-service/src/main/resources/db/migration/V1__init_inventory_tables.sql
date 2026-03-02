CREATE TABLE IF NOT EXISTS inventory_items (
  id BIGSERIAL PRIMARY KEY,
  sku VARCHAR(64) NOT NULL UNIQUE,
  available_quantity INT NOT NULL DEFAULT 0,
  reserved_quantity INT NOT NULL DEFAULT 0,
  warehouse_code VARCHAR(64) NOT NULL,
  updated_at TIMESTAMP
);
CREATE INDEX IF NOT EXISTS idx_inventory_sku ON inventory_items(sku);
