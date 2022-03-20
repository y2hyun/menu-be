package com.yang.menu.menuapi.web.controller

import com.yang.menu.menuapi.service.UserService
import com.yang.menu.menuapi.web.api.SignupApi
import com.yang.menu.menuapi.web.payload.CommonResponse
import com.yang.menu.menuapi.web.payload.SignupRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class SignupController(
  private val userService: UserService
) : SignupApi {
  override fun signup(signupRequest: SignupRequest): ResponseEntity<CommonResponse> =
    if (this.userService.createUser(signupRequest))
      ResponseEntity.ok().build()
    else
      ResponseEntity(
        CommonResponse(message = "exists user"),
        HttpStatus.BAD_REQUEST
      )
}