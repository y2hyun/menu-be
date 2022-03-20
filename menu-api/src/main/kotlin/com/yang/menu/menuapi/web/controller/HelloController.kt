package com.yang.menu.menuapi.web.controller

import com.yang.menu.jwtauth.security.JwtUserDetails
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/hello")
class HelloController {

  @GetMapping
  fun hello(@AuthenticationPrincipal principal: JwtUserDetails): ResponseEntity<HelloResponse> =
    ResponseEntity.ok(
      HelloResponse(
        message = "hello ${principal.username} id=${principal.id} status=${principal.getStatus()}"
      )
    )

  @GetMapping("/secure")
  fun secureHello() = ResponseEntity.ok(
    HelloResponse()
  )
}

data class HelloResponse(
  val message: String = "hello"
)