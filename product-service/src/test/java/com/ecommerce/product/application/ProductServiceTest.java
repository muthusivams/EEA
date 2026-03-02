package com.ecommerce.product.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.ecommerce.product.domain.Product;
import com.ecommerce.product.dto.ProductRequest;
import com.ecommerce.product.dto.ProductResponse;
import com.ecommerce.product.mapper.ProductMapper;
import com.ecommerce.product.repository.ProductRepository;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
  @Mock ProductRepository repository;
  @Mock ProductMapper mapper;
  @Mock MeterRegistry meterRegistry;
  @Mock Counter counter;
  @InjectMocks ProductService service;

  @Test
  void shouldGetBySku() {
    Product product = Product.builder().id(1L).sku("SKU-1").active(true).build();
    ProductResponse response = new ProductResponse(1L, "SKU-1", "N", "D", BigDecimal.TEN, 4, true);
    when(repository.findBySkuAndActiveTrue("SKU-1")).thenReturn(Optional.of(product));
    when(mapper.toResponse(product)).thenReturn(response);
    assertEquals("SKU-1", service.getBySku("SKU-1").sku());
  }

  @Test
  void shouldCreateProductAndEmitMetric() {
    ProductRequest request = new ProductRequest("SKU-2", "P2", "D", BigDecimal.ONE, 5, true);
    Product entity = Product.builder().id(2L).sku("SKU-2").build();
    ProductResponse response = new ProductResponse(2L, "SKU-2", "P2", "D", BigDecimal.ONE, 5, true);
    when(mapper.toEntity(request)).thenReturn(entity);
    when(repository.save(any(Product.class))).thenReturn(entity);
    when(mapper.toResponse(entity)).thenReturn(response);
    when(meterRegistry.counter("products_created_total")).thenReturn(counter);

    assertEquals("SKU-2", service.create(request).sku());
    verify(counter).increment();
  }
}
