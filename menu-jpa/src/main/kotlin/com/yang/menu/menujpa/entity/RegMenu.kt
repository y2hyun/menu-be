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
import javax.persistence.Table

@Entity
@Table(name = "reg_menu")
class RegMenu {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  var id: Int? = null

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "menu_category_id", nullable = false)
  var menuCategory: MenuCategory? = null

  @Column(name = "name", nullable = false)
  var name: String? = null

  @Column(name = "created_at", nullable = false)
  var createdAt: Instant? = null

  @Column(name = "updated_at")
  var updatedAt: Instant? = null
}