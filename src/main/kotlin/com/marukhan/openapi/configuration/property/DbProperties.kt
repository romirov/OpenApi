package com.marukhan.openapi.configuration.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

@ConfigurationProperties("spring.datasource")
data class DbProperties @ConstructorBinding constructor(
	val databaseName: String,
	val username: String,
	val password: String
)