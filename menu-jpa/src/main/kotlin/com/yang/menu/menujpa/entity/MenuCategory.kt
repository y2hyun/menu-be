package com.yang.menu.menujpa.entity

import java.time.Instant
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "menu_category")
class MenuCategory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  var id: Int? = null

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "user_id", nullable = false)
  var user: User? = null

  @Column(name = "name", nullable = false)
  var name: String? = null

  @Column(name = "created_at", nullable = false)
  var createdAt: Instant? = null

  @Column(name = "updated_at")
  var updatedAt: Instant? = null

  @OneToMany(mappedBy = "menuCategory")
  var regMenus: MutableSet<RegMenu> = mutableSetOf()
}