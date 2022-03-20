package com.yang.menu.jwtauth.security

import com.yang.menu.jwtauth.components.JwtUtils
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.stereotype.Component
import org.springframework.util.ClassUtils

@Component
class JwtAuthenticationProvider(
  private val jwtUtils: JwtUtils
) : AuthenticationProvider {
  /**
   * Performs authentication with the same contract as
   * [org.springframework.security.authentication.AuthenticationManager.authenticate]
   * .
   * @param authentication the authentication request object.
   * @return a fully authenticated object including credentials. May return
   * `null` if the `AuthenticationProvider` is unable to support
   * authentication of the passed `Authentication` object. In such a case,
   * the next `AuthenticationProvider` that supports the presented
   * `Authentication` class will be tried.
   * @throws AuthenticationException if authentication fails.
   */
  override fun authenticate(authentication: Authentication?): Authentication {
    val authToken = authentication as JwtAuthenticationToken
    val credential = authToken.credentials
    if (jwtUtils.validateJwtToken(credential)) {
      return JwtAuthenticationToken(jwtUtils.getJwtUserDetails(credential), credential).apply {
        isAuthenticated = true
      }
    }
    return authentication
  }

  /**
   * Returns `true` if this <Code>AuthenticationProvider</Code> supports the
   * indicated <Code>Authentication</Code> object.
   *
   *
   * Returning `true` does not guarantee an
   * `AuthenticationProvider` will be able to authenticate the presented
   * instance of the `Authentication` class. It simply indicates it can
   * support closer evaluation of it. An `AuthenticationProvider` can still
   * return `null` from the [.authenticate] method to
   * indicate another `AuthenticationProvider` should be tried.
   *
   *
   *
   * Selection of an `AuthenticationProvider` capable of performing
   * authentication is conducted at runtime the `ProviderManager`.
   *
   * @param authentication
   * @return `true` if the implementation can more closely evaluate the
   * `Authentication` class presented
   */
  override fun supports(authentication: Class<*>?): Boolean {
    if (authentication != null) {
      return ClassUtils.isAssignable(JwtAuthenticationToken::class.java, authentication)
    }
    return false
  }
}