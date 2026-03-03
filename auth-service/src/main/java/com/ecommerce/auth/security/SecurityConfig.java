package com.ecommerce.auth.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth.requestMatchers("/auth/**", "/actuator/health", "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll().anyRequest().authenticated())
        .headers(h -> h.httpStrictTransportSecurity(hsts -> hsts.includeSubDomains(true).maxAgeInSeconds(31536000)));
    return http.build();
  }

  @Bean
  PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }
}
