package com.yang.menu.jwtauth.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "jwtauth")
data class JwtAuthProperties(
  val noAuthUrlPatterns: List<String>,
  val secret: String,
  val expireMs: Long,
  val issuer: String
)