package com.yang.menu.menujpa.entity.composite

import org.hibernate.Hibernate
import javax.persistence.Embeddable
import java.io.Serializable
import java.util.*
import javax.persistence.Column

@Embeddable
class SubscribeKey : Serializable {
  @Column(name = "user_id", nullable = false)
  var userId: Int? = null

  @Column(name = "target_user_id", nullable = false)
  var targetUserId: Int? = null

  override fun hashCode(): Int = Objects.hash(userId, targetUserId)
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false

    other as SubscribeKey

    return userId == other.userId && targetUserId == other.targetUserId
  }

  companion object {
    private const val serialVersionUID = -7105284289761145247L
  }
}