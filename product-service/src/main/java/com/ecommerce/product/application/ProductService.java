package com.ecommerce.product.application;

import com.ecommerce.product.domain.Product;
import com.ecommerce.product.dto.ProductRequest;
import com.ecommerce.product.dto.ProductResponse;
import com.ecommerce.product.exception.NotFoundException;
import com.ecommerce.product.mapper.ProductMapper;
import com.ecommerce.product.repository.ProductRepository;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository repository;
  private final ProductMapper mapper;
  private final MeterRegistry meterRegistry;

  public ProductResponse create(ProductRequest request) {
    Product saved = repository.save(mapper.toEntity(request));
    meterRegistry.counter("products_created_total").increment();
    log.info("Product created sku={} id={}", saved.getSku(), saved.getId());
    return mapper.toResponse(saved);
  }

  @Cacheable(cacheNames = "productBySku", key = "#sku")
  public ProductResponse getBySku(String sku) {
    return repository.findBySkuAndActiveTrue(sku)
        .map(mapper::toResponse)
        .orElseThrow(() -> new NotFoundException("Product not found"));
  }
}
