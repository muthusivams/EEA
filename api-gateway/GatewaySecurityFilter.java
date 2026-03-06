package com.ecommerce.gateway.security;

import java.time.Duration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class GatewaySecurityFilter implements GlobalFilter, Ordered {

  private final ReactiveJwtDecoder jwtDecoder;

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    String correlationId = java.util.UUID.randomUUID().toString();
    exchange.getRequest().mutate().header("X-Correlation-Id", correlationId).build();

    String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      return chain.filter(exchange);
    }

    String token = authHeader.substring(7);
    long start = System.nanoTime();

    return jwtDecoder.decode(token)
        .timeout(Duration.ofSeconds(2))
        .doOnNext(jwt -> log.info("Gateway JWT validated sub={} corrId={}", jwt.getSubject(), correlationId))
        .map(Jwt::getTokenValue)
        .flatMap(v -> chain.filter(exchange))
        .doFinally(signal -> {
          long ms = (System.nanoTime() - start) / 1_000_000;
          log.info("Gateway request path={} ms={} corrId={}", exchange.getRequest().getPath(), ms, correlationId);
        });
  }

  @Override
  public int getOrder() {
    return -1;
  }
}
