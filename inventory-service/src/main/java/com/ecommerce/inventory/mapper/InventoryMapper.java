package com.ecommerce.inventory.mapper;
import com.ecommerce.inventory.domain.InventoryItem;
import com.ecommerce.inventory.dto.InventoryResponse;
import org.springframework.stereotype.Component;
@Component
public class InventoryMapper { public InventoryResponse toResponse(InventoryItem i){ return new InventoryResponse(i.getSku(),i.getAvailableQuantity(),i.getReservedQuantity(),i.getWarehouseCode()); } }
