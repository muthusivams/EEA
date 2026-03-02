package com.ecommerce.inventory.exception;
import java.util.Map;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<Map<String,String>> handle(NotFoundException ex){ return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error",ex.getMessage())); }
}
