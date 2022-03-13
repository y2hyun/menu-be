package com.yang.menu.menuapi.service

import com.yang.menu.menuapi.web.payload.SignupRequest
import com.yang.menu.menujpa.entity.DefaultSubscribe
import com.yang.menu.menujpa.entity.User
import com.yang.menu.menujpa.repository.UserRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

@Service
class UserService(
  private val userRepository: UserRepository
) {
  private val logger = KotlinLogging.logger {}

  @Transactional
  fun createUser(signupReq: SignupRequest): Boolean {
    if (this.userRepository.findByUsernameOrEmail(signupReq.username, signupReq.email) != null) {
      return false
    }
    val user = this.userRepository.save(
      User().apply {
        this.username = signupReq.username
        this.password = signupReq.password // TODO hashing
        this.displayName = signupReq.displayName
        this.email = signupReq.email
        this.status = "0"
        this.createdAt = Instant.now()
      }
    )
    val defaultSubscribe = DefaultSubscribe().apply {
      this.id = user.id!!
      this.user = user
      this.defaultSubscribeUser = user
    }
    user.defaultSubscribes = defaultSubscribe
    userRepository.save(user)
    logger.debug("user created. : [${user.id}]")
    return true
  }

  fun fetchUser(id: Int) = userRepository.findById(id)
}