package com.yang.menu.jwtauth.security

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthenticationFilter : OncePerRequestFilter() {
  /**
   * Same contract as for `doFilter`, but guaranteed to be
   * just invoked once per request within a single request thread.
   * See [.shouldNotFilterAsyncDispatch] for details.
   *
   * Provides HttpServletRequest and HttpServletResponse arguments instead of the
   * default ServletRequest and ServletResponse ones.
   */
  override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
    request.cookies?.firstOrNull { it.name == "token" }?.value?.let {
      SecurityContextHolder.getContext().authentication = JwtAuthenticationToken(it)
    }
    filterChain.doFilter(request, response)
  }
}