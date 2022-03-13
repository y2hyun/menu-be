package com.yang.menu.menujpa.entity

import java.time.Instant
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Index
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(
  name = "menu",
  indexes = [Index(name = "idx_menu_target_date", columnList = "target_date")]
)
class Menu {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  var id: Int? = null

  @Column(name = "target_date", nullable = false)
  var targetDate: LocalDate? = null

  @Column(name = "name")
  var name: String? = null

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "user_id", nullable = false)
  var user: User? = null

  @Column(name = "created_at", nullable = false)
  var createdAt: Instant? = null

  @Column(name = "updated_at")
  var updatedAt: Instant? = null

  @OneToMany(mappedBy = "menu")
  var buys: MutableSet<Buy> = mutableSetOf()
}