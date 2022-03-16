package com.yang.menu.menuapi.web.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/hello")
class HelloController {

  @GetMapping
  fun hello(): ResponseEntity<HelloResponse> =
    ResponseEntity.ok(
      HelloResponse()
    )
}

data class HelloResponse(
  val message: String = "hello"
)