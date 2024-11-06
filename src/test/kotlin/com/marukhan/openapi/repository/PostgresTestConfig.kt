package com.marukhan.openapi.repository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.test.context.ActiveProfiles
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.utility.DockerImageName

@TestConfiguration
@ActiveProfiles("test")
class PostgresTestConfig {
		@Autowired
		lateinit var dataSourceProperties: DataSourceProperties

		@Bean
		fun postgreSQLContainer(): PostgreSQLContainer<*> =
			PostgreSQLContainer(DockerImageName.parse("postgres:16-alpine"))
				.withDatabaseName(dataSourceProperties.url.split("/").last())
				.withUsername(dataSourceProperties.username)
				.withPassword(dataSourceProperties.password)
}