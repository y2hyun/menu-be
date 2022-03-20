package com.yang.menu.menuapi.web.payload

import com.fasterxml.jackson.annotation.JsonProperty

/**
 *
 * @param code
 * @param message
 */
data class CommonResponse(

  @field:JsonProperty("code") val code: kotlin.String? = null,

  @field:JsonProperty("message") val message: kotlin.String? = null
)