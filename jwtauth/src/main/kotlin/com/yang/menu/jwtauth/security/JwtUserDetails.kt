package com.yang.menu.jwtauth.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class JwtUserDetails(
  val id: Int,
  private val username: String,
  private val status: String
) : UserDetails {
  /**
   * Returns the authorities granted to the user. Cannot return `null`.
   * @return the authorities, sorted by natural key (never `null`)
   */
  override fun getAuthorities(): MutableCollection<out GrantedAuthority> = mutableSetOf()

  /**
   * Returns the password used to authenticate the user.
   * @return the password
   */
  override fun getPassword(): String = ""

  /**
   * Returns the username used to authenticate the user. Cannot return
   * `null`.
   * @return the username (never `null`)
   */
  override fun getUsername() = username

  /**
   * Indicates whether the user's account has expired. An expired account cannot be
   * authenticated.
   * @return `true` if the user's account is valid (ie non-expired),
   * `false` if no longer valid (ie expired)
   */
  override fun isAccountNonExpired() = true

  /**
   * Indicates whether the user is locked or unlocked. A locked user cannot be
   * authenticated.
   * @return `true` if the user is not locked, `false` otherwise
   */
  override fun isAccountNonLocked() = true

  /**
   * Indicates whether the user's credentials (password) has expired. Expired
   * credentials prevent authentication.
   * @return `true` if the user's credentials are valid (ie non-expired),
   * `false` if no longer valid (ie expired)
   */
  override fun isCredentialsNonExpired() = true

  /**
   * Indicates whether the user is enabled or disabled. A disabled user cannot be
   * authenticated.
   * @return `true` if the user is enabled, `false` otherwise
   */
  override fun isEnabled() = true

  fun getStatus() = status
}