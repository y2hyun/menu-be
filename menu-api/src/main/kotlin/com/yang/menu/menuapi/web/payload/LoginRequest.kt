package com.yang.menu.menuapi.web.payload

import com.fasterxml.jackson.annotation.JsonProperty

/**
 *
 * @param username
 * @param password
 */
data class LoginRequest(

  @field:JsonProperty("username", required = true) val username: kotlin.String,

  @field:JsonProperty("password", required = true) val password: kotlin.String
)