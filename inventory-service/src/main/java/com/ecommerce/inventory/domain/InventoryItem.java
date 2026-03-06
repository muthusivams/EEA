package com.ecommerce.inventory.domain;

import jakarta.persistence.*;
import java.time.Instant;
import lombok.*;

@Entity @Table(name="inventory_items")
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class InventoryItem {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  @Column(nullable=false, unique=true) private String sku;
  @Column(nullable=false) private Integer availableQuantity;
  @Column(nullable=false) private Integer reservedQuantity;
  @Column(nullable=false) private String warehouseCode;
  private Instant updatedAt;
}
