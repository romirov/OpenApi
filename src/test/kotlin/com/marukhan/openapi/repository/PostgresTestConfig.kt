package com.marukhan.openapi.repository

import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.test.context.ActiveProfiles
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.utility.DockerImageName

@TestConfiguration
@ActiveProfiles("test")
class PostgresTestConfig {
//    @Autowired


    @Bean
    fun postgreSQLContainer(dataSource: HikariDataSource): PostgreSQLContainer<*> =
            PostgreSQLContainer(DockerImageName.parse("postgres:16-alpine"))
}