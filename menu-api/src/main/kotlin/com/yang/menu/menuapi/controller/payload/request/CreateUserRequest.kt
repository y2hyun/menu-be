package com.yang.menu.menuapi.controller.payload.request

data class CreateUserRequest(
  val username: String,
  val password: String,
  val displayName: String,
  val email: String,
)