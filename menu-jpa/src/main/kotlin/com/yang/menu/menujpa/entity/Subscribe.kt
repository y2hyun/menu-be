package com.yang.menu.menujpa.entity

import com.yang.menu.menujpa.entity.composite.SubscribeKey
import java.time.Instant
import javax.persistence.Column
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.MapsId
import javax.persistence.Table

@Entity
@Table(name = "subscribe")
class Subscribe {
  @EmbeddedId
  var id: SubscribeKey? = null

  @MapsId("userId")
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "user_id", nullable = false)
  var user: User? = null

  @MapsId("targetUserId")
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "target_user_id", nullable = false)
  var targetUser: User? = null

  @Column(name = "status", nullable = false, length = 1)
  var status: String? = null

  @Column(name = "created_at", nullable = false)
  var createdAt: Instant? = null

  @Column(name = "updated_at")
  var updatedAt: Instant? = null
}