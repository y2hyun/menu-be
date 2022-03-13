package com.yang.menu.menuapi.web.payload

import com.fasterxml.jackson.annotation.JsonProperty

/**
 *
 * @param username
 * @param password
 * @param email
 * @param displayName
 */
data class SignupRequest(

  @field:JsonProperty("username", required = true) val username: kotlin.String,

  @field:JsonProperty("password", required = true) val password: kotlin.String,

  @field:JsonProperty("email", required = true) val email: kotlin.String,

  @field:JsonProperty("displayName") val displayName: kotlin.String? = null
)