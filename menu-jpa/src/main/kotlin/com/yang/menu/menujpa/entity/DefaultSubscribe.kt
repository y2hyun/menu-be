package com.yang.menu.menujpa.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.MapsId
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "default_subscribe")
class DefaultSubscribe {
  @Id
  @Column(name = "user_id", nullable = false)
  var id: Int? = null

  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @MapsId
  @JoinColumn(name = "user_id")
  var user: User? = null

  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "default_subscribe_user_id", nullable = false)
  var defaultSubscribeUser: User? = null
}