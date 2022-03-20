package com.yang.menu.jwtauth.components

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.yang.menu.jwtauth.properties.JwtAuthProperties
import com.yang.menu.jwtauth.security.JwtUserDetails
import mu.KotlinLogging
import org.springframework.stereotype.Component
import java.time.Instant
import java.util.Date

@Component
class JwtUtils(
  private val jwtAuthProperties: JwtAuthProperties
) {
  private val logger = KotlinLogging.logger { }
  private val algorithm = Algorithm.HMAC512(jwtAuthProperties.secret)

  fun generateJwtToken(subject: String, claims: Map<String, Any>): String =
    JWT.create().apply {
      val currentDate = Instant.now()
      this.withSubject(subject)
      this.withIssuedAt(Date.from(currentDate))
      this.withIssuer(jwtAuthProperties.issuer)
      this.withExpiresAt(Date.from(currentDate.plusMillis(jwtAuthProperties.expireMs)))
      this.withPayload(claims)
    }.sign(algorithm)

  fun validateJwtToken(token: String) =
    try {
      JWT.require(algorithm).build().verify(token)
      true
    } catch (e: Exception) {
      logger.warn { e.message }
      false
    }

  fun getSubject(token: String): String {
    return JWT.require(algorithm).build().verify(token).subject
  }
  fun getJwtUserDetails(token: String): JwtUserDetails {
    val decodedJwt = JWT.require(algorithm).build().verify(token)
    return JwtUserDetails(
      id = decodedJwt.claims["id"]?.asInt()!!,
      username = decodedJwt.subject,
      status = decodedJwt.claims["status"]?.asString()!!
    )
  }
}