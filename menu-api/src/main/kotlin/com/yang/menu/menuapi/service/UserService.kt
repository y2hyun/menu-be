package com.yang.menu.menuapi.service

import com.yang.menu.jwtauth.components.JwtUtils
import com.yang.menu.menuapi.exceptions.InvalidPasswordException
import com.yang.menu.menuapi.exceptions.NotExistsUserException
import com.yang.menu.menuapi.web.payload.SignupRequest
import com.yang.menu.menujpa.entity.DefaultSubscribe
import com.yang.menu.menujpa.entity.User
import com.yang.menu.menujpa.repository.UserRepository
import mu.KotlinLogging
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

@Service
class UserService(
  private val userRepository: UserRepository,
  private val passwordEncoder: PasswordEncoder,
  private val jwtUtils: JwtUtils
) {
  private val logger = KotlinLogging.logger {}

  @Transactional
  fun createUser(signupReq: SignupRequest): Boolean {
    if (this.userRepository.existsByUsernameOrEmail(signupReq.username, signupReq.email)) {
      return false
    }
    val user = this.userRepository.save(
      User().apply {
        this.username = signupReq.username
        this.password = passwordEncoder.encode(signupReq.password)
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

  @Throws(NotExistsUserException::class, InvalidPasswordException::class)
  fun login(username: String, password: String): String {
    val user = userRepository.findByUsername(username) ?: throw NotExistsUserException()
    if (!passwordEncoder.matches(password, user.password)) throw InvalidPasswordException()
    return jwtUtils.generateJwtToken(user.username!!, mapOf("uid" to user.id!!, "sts" to user.status!!))
  }

  fun fetchUser(id: Int) = userRepository.findById(id)
}