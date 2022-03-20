package com.yang.menu.menuapi.web.controller

import com.yang.menu.menuapi.exceptions.MenuException
import com.yang.menu.menuapi.service.UserService
import com.yang.menu.menuapi.web.api.LoginApi
import com.yang.menu.menuapi.web.payload.CommonResponse
import com.yang.menu.menuapi.web.payload.LoginRequest
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseCookie
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController(
  private val userService: UserService
) : LoginApi {
  override fun login(loginRequest: LoginRequest): ResponseEntity<CommonResponse> {
    return try {
      val token = userService.login(loginRequest.username, loginRequest.password)
      ResponseEntity.ok().header(
        HttpHeaders.SET_COOKIE,
        ResponseCookie.from("token", token).maxAge((60 * 30)).httpOnly(true).build().toString()
      ).body(
        CommonResponse(code = "0", message = "login success")
      )
    } catch (e: MenuException) {
      ResponseEntity(
        CommonResponse(message = e.message),
        HttpStatus.FORBIDDEN
      )
    }
  }
}