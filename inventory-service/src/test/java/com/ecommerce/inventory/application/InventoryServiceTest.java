package com.ecommerce.inventory.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.ecommerce.inventory.domain.InventoryItem;
import com.ecommerce.inventory.dto.InventoryResponse;
import com.ecommerce.inventory.dto.InventoryUpsertRequest;
import com.ecommerce.inventory.mapper.InventoryMapper;
import com.ecommerce.inventory.repository.InventoryRepository;
import io.micrometer.core.instrument.MeterRegistry;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class InventoryServiceTest {
  @Mock InventoryRepository repository; @Mock InventoryMapper mapper; @Mock MeterRegistry meterRegistry; @InjectMocks InventoryService service;

  @Test void shouldUpsert(){
    InventoryItem i = InventoryItem.builder().sku("SKU-1").availableQuantity(10).reservedQuantity(2).warehouseCode("W1").build();
    when(repository.findBySku("SKU-1")).thenReturn(Optional.empty());
    when(repository.save(any(InventoryItem.class))).thenReturn(i);
    when(mapper.toResponse(i)).thenReturn(new InventoryResponse("SKU-1",10,2,"W1"));
    assertEquals("SKU-1", service.upsert(new InventoryUpsertRequest("SKU-1",10,2,"W1")).sku());
  }

  @Test void shouldGet(){
    InventoryItem i = InventoryItem.builder().sku("SKU-2").availableQuantity(4).reservedQuantity(1).warehouseCode("W1").build();
    when(repository.findBySku("SKU-2")).thenReturn(Optional.of(i));
    when(mapper.toResponse(i)).thenReturn(new InventoryResponse("SKU-2",4,1,"W1"));
    assertEquals("SKU-2", service.getBySku("SKU-2").sku());
  }
}
