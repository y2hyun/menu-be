package com.yang.menu.jwtauth.security

import mu.KotlinLogging
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAuthenticationEntryPoint : AuthenticationEntryPoint {

  private val logger = KotlinLogging.logger {}

  /**
   * Commences an authentication scheme.
   *
   *
   * `ExceptionTranslationFilter` will populate the `HttpSession`
   * attribute named
   * `AbstractAuthenticationProcessingFilter.SPRING_SECURITY_SAVED_REQUEST_KEY`
   * with the requested target URL before calling this method.
   *
   *
   * Implementations should modify the headers on the `ServletResponse` as
   * necessary to commence the authentication process.
   * @param request that resulted in an `AuthenticationException`
   * @param response so that the user agent can begin authentication
   * @param authException that caused the invocation
   */
  override fun commence(
    request: HttpServletRequest?,
    response: HttpServletResponse?,
    authException: AuthenticationException?
  ) {
    logger.warn { "Unauthorized error: ${authException?.message}" }
    response?.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized")
  }
}