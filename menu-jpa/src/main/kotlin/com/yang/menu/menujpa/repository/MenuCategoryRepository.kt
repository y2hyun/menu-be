package com.yang.menu.menujpa.repository

import com.yang.menu.menujpa.entity.MenuCategory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MenuCategoryRepository : JpaRepository<MenuCategory, Int>