package com.ecommerce.inventory.controller;
import com.ecommerce.inventory.application.InventoryService;
import com.ecommerce.inventory.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/inventory") @RequiredArgsConstructor
public class InventoryController {
  private final InventoryService inventoryService;
  @PostMapping public ResponseEntity<InventoryResponse> upsert(@Valid @RequestBody InventoryUpsertRequest request){ return ResponseEntity.ok(inventoryService.upsert(request)); }
  @GetMapping("/{sku}") public ResponseEntity<InventoryResponse> get(@PathVariable String sku){ return ResponseEntity.ok(inventoryService.getBySku(sku)); }
}
