package com.yang.menu.menujpa.entity

import java.time.Instant
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Index
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.PrimaryKeyJoinColumn
import javax.persistence.Table

@Entity
@Table(
  name = "users",
  indexes = [Index(name = "users_username_key", columnList = "username", unique = true)]
)
class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  var id: Int? = null

  @Column(name = "username", nullable = false, length = 100)
  var username: String? = null

  @Column(name = "password", nullable = false)
  var password: String? = null

  @Column(name = "display_name", nullable = false, length = 100)
  var displayName: String? = null

  @Column(name = "email", nullable = false)
  var email: String? = null

  @Column(name = "status", nullable = false, length = 1)
  var status: String? = null

  @Column(name = "created_at", nullable = false)
  var createdAt: Instant? = null

  @Column(name = "updated_at")
  var updatedAt: Instant? = null

  @OneToOne(mappedBy = "defaultSubscribeUser", cascade = [CascadeType.ALL])
  @PrimaryKeyJoinColumn
  var defaultSubscribes: DefaultSubscribe? = null

  @OneToMany(mappedBy = "user")
  var subscribes: MutableSet<Subscribe> = mutableSetOf()
}