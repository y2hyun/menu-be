package com.yang.menu.menujpa.repository

import com.yang.menu.menujpa.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Int> {
  fun findByUsernameOrEmail(username: String, email: String): User?
}