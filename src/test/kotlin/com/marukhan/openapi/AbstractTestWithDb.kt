package com.marukhan.openapi

import com.marukhan.openapi.configuration.PostgresTestConfig
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.testcontainers.containers.PostgreSQLContainer

@SpringBootTest(classes = [PostgresTestConfig::class])
@ActiveProfiles("test")
@TestInstance(PER_CLASS)
abstract class AbstractTestWithDb {
		@Autowired
		lateinit var postgresContainer: PostgreSQLContainer<*>

		@BeforeAll
		fun beforeAll() = postgresContainer.start()

		@AfterAll
		fun afterAll() = postgresContainer.stop()
}