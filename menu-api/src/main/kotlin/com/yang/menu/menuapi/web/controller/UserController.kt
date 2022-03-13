package com.yang.menu.menuapi.web.controller

import com.yang.menu.menuapi.service.UserService
import com.yang.menu.menuapi.web.api.UserApi
import com.yang.menu.menuapi.web.payload.SignupRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
  private val userService: UserService
) : UserApi {
  override fun signup(signupRequest: SignupRequest): ResponseEntity<Unit> =
    if (this.userService.createUser(signupRequest))
      ResponseEntity.ok().build()
    else
      ResponseEntity(
        HttpStatus.BAD_REQUEST
      )
}