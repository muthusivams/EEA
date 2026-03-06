package com.ecommerce.gateway.security;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.web.server.MockServerWebExchange;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import reactor.core.publisher.Mono;

class GatewaySecurityFilterTest {
  @Test
  void shouldPassWhenJwtIsValid() {
    ReactiveJwtDecoder decoder = mock(ReactiveJwtDecoder.class);
    GatewaySecurityFilter filter = new GatewaySecurityFilter(decoder);
    Jwt jwt = Jwt.withTokenValue("token").header("alg", "RS256").claim("sub", "u1").build();
    when(decoder.decode("token")).thenReturn(Mono.just(jwt));

    MockServerHttpRequest request = MockServerHttpRequest.get("/api/v1/users/me")
        .header(HttpHeaders.AUTHORIZATION, "Bearer token")
        .build();
    MockServerWebExchange exchange = MockServerWebExchange.from(request);

    filter.filter(exchange, mock(GatewayFilterChain.class));
  }
}
