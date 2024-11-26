package com.marukhan.openapi

import com.marukhan.openapi.repository.PostgresTestConfig
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(classes = [PostgresTestConfig::class])
@ActiveProfiles("test")
class OpenApiApplicationTests {

	@Test
	fun contextLoads() {
	}

}
