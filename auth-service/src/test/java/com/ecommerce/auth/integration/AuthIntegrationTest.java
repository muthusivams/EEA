package com.ecommerce.auth.integration;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest
class AuthIntegrationTest {
  @Container
  static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0.39")
      .withDatabaseName("authdb")
      .withUsername("test")
      .withPassword("test");

  @DynamicPropertySource
  static void registerProps(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", () -> {
      String url = mysql.getJdbcUrl();
      return url + (url.contains("?") ? "&" : "?") + "createDatabaseIfNotExist=true";
    });
    registry.add("spring.datasource.username", mysql::getUsername);
    registry.add("spring.datasource.password", mysql::getPassword);
    registry.add("spring.jpa.hibernate.ddl-auto", () -> "none");
  }

  @Test
  void contextLoads() {}
}
