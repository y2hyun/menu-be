package com.yang.menu.jwtauth.config

import com.yang.menu.jwtauth.properties.JwtAuthProperties
import com.yang.menu.jwtauth.security.JwtAuthenticationEntryPoint
import com.yang.menu.jwtauth.security.JwtAuthenticationFilter
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.config.web.servlet.invoke
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@Configuration
@ConfigurationPropertiesScan(basePackages = ["com.yang.menu.jwtauth.properties"])
@ComponentScan(basePackages = ["com.yang.menu.jwtauth"])
class WebSecurityConfig(
  private val jwtAuthProperties: JwtAuthProperties,
  private val jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint
) {
  @Bean
  fun filterChain(http: HttpSecurity): SecurityFilterChain {
    http {
      csrf { disable() }
      sessionManagement { sessionCreationPolicy = SessionCreationPolicy.STATELESS }
      exceptionHandling { authenticationEntryPoint = jwtAuthenticationEntryPoint }
      authorizeRequests {
        jwtAuthProperties.noAuthUrlPatterns.forEach {
          authorize(AntPathRequestMatcher(it), permitAll)
        }
        authorize(anyRequest, authenticated)
      }
      addFilterBefore<UsernamePasswordAuthenticationFilter>(authenticationJwtTokenFilter())
    }
    return http.build()
  }

  @ConditionalOnMissingBean
  @Bean
  fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

  @Bean
  fun authenticationJwtTokenFilter(): JwtAuthenticationFilter = JwtAuthenticationFilter()
}