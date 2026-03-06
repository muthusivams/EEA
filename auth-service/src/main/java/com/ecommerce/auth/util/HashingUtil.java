package com.ecommerce.auth.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import lombok.SneakyThrows;

public final class HashingUtil {
  private HashingUtil() {}
  @SneakyThrows
  public static String sha256(String value) {
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    byte[] digest = md.digest(value.getBytes(StandardCharsets.UTF_8));
    StringBuilder sb = new StringBuilder();
    for (byte b : digest) sb.append(String.format("%02x", b));
    return sb.toString();
  }
}
