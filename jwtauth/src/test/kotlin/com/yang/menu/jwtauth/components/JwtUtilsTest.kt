package com.yang.menu.jwtauth.components

import com.yang.menu.jwtauth.properties.JwtAuthProperties
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue

internal class JwtUtilsTest {

  private val jwtAuthProperties = JwtAuthProperties(
    noAuthUrlPatterns = emptyList(),
    secret = "hoge",
    expireMs = 1000 * 60 * 10,
    issuer = "tester"
  )

  private val jwtUtils = JwtUtils(jwtAuthProperties)

  @Test
  fun generateJwtToken() {
    val token = jwtUtils.generateJwtToken("hello", emptyMap())
    assertTrue(token.isNotEmpty())
  }

  @Test
  fun validateJwtToken() {
    val token = jwtUtils.generateJwtToken("hello", emptyMap())
    assertTrue(jwtUtils.validateJwtToken(token))
  }

  @Test
  fun validateJwtToken_Invalid() {
    val fakeToken =
      "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9." +
        "eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ." +
        "uk1qJnGuGHHGFw6fXpVILrdo52JqyD3EzvW3_DxhgZPAqU-OKzzPy7xdRNeQRba5CI6VGmlo6DBYqRCteiiOTw"
    assertFalse(jwtUtils.validateJwtToken(fakeToken))
  }

  @Test
  fun getSubject() {
    val subject = "hello"
    val token = jwtUtils.generateJwtToken(subject, emptyMap())
    assertEquals(subject, jwtUtils.getSubject(token))
  }
}