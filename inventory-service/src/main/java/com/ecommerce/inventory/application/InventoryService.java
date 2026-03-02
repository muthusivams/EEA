package com.ecommerce.inventory.application;

import com.ecommerce.inventory.domain.InventoryItem;
import com.ecommerce.inventory.dto.InventoryResponse;
import com.ecommerce.inventory.dto.InventoryUpsertRequest;
import com.ecommerce.inventory.exception.NotFoundException;
import com.ecommerce.inventory.mapper.InventoryMapper;
import com.ecommerce.inventory.repository.InventoryRepository;
import io.micrometer.core.instrument.MeterRegistry;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j @Service @RequiredArgsConstructor
public class InventoryService {
  private final InventoryRepository repository;
  private final InventoryMapper mapper;
  private final MeterRegistry meterRegistry;

  public InventoryResponse upsert(InventoryUpsertRequest request){
    InventoryItem item = repository.findBySku(request.sku())
      .map(existing -> {
        existing.setAvailableQuantity(request.availableQuantity());
        existing.setReservedQuantity(request.reservedQuantity());
        existing.setWarehouseCode(request.warehouseCode());
        existing.setUpdatedAt(Instant.now());
        return existing;
      })
      .orElseGet(() -> InventoryItem.builder()
        .sku(request.sku())
        .availableQuantity(request.availableQuantity())
        .reservedQuantity(request.reservedQuantity())
        .warehouseCode(request.warehouseCode())
        .updatedAt(Instant.now())
        .build());

    InventoryItem saved = repository.save(item);
    meterRegistry.gauge("inventory_available_quantity", saved, s -> s.getAvailableQuantity().doubleValue());
    log.info("Inventory upserted sku={} available={} reserved={}", saved.getSku(), saved.getAvailableQuantity(), saved.getReservedQuantity());
    return mapper.toResponse(saved);
  }

  public InventoryResponse getBySku(String sku){
    return repository.findBySku(sku).map(mapper::toResponse).orElseThrow(() -> new NotFoundException("Inventory item not found"));
  }
}
