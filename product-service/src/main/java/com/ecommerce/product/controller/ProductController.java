package com.ecommerce.product.controller;

import com.ecommerce.product.application.ProductService;
import com.ecommerce.product.dto.ProductRequest;
import com.ecommerce.product.dto.ProductResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @PostMapping
  public ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductRequest request) {
    return ResponseEntity.ok(productService.create(request));
  }

  @GetMapping("/{sku}")
  public ResponseEntity<ProductResponse> getBySku(@PathVariable String sku) {
    return ResponseEntity.ok(productService.getBySku(sku));
  }
}
