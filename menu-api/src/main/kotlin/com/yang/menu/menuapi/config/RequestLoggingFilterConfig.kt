package com.yang.menu.menuapi.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.filter.CommonsRequestLoggingFilter

@Configuration
class RequestLoggingFilterConfig {
  @Bean
  fun loggingFilter() =
    CommonsRequestLoggingFilter().apply {
      this.setIncludeHeaders(false)
      this.setIncludeQueryString(true)
      this.setIncludePayload(true)
      this.setMaxPayloadLength(200)
      this.setAfterMessagePrefix("REQUEST DATA : ")
    }
}