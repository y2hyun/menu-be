package com.yang.menu.menujpa.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories(basePackages = ["com.yang.menu.menujpa.repository"])
@EntityScan(basePackages = ["com.yang.menu.menujpa.entity"])
@ComponentScan(basePackages = ["com.yang.menu.menujpa"])
class JpaConfig