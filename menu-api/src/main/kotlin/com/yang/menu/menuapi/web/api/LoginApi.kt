/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (5.4.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
*/
package com.yang.menu.menuapi.web.api

import com.yang.menu.menuapi.web.payload.CommonResponse
import com.yang.menu.menuapi.web.payload.LoginRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

import org.springframework.web.bind.annotation.*
import org.springframework.validation.annotation.Validated

import javax.validation.Valid

@Validated
@RequestMapping("\${api.base-path:}")
interface LoginApi {

  @RequestMapping(
    method = [RequestMethod.POST],
    value = ["/login"],
    produces = ["application/json"],
    consumes = ["application/json"]
  )
  fun login(
    @Valid @RequestBody loginRequest: LoginRequest
  ): ResponseEntity<CommonResponse> {
    return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
  }
}