package com.marukhan.openapi.configuration

import com.github.dockerjava.api.model.ExposedPort
import com.github.dockerjava.api.model.HostConfig
import com.github.dockerjava.api.model.PortBinding
import com.github.dockerjava.api.model.Ports
import com.marukhan.openapi.configuration.property.DbProperties
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.test.context.ActiveProfiles
import org.testcontainers.containers.PostgreSQLContainer

@TestConfiguration
@ActiveProfiles("test")
class PostgresTestConfig {
		@Autowired
		lateinit var dbProperties: DbProperties
		private val initScript = "init.sql"

		@Bean
		fun postgresContainer(): PostgreSQLContainer<*> {
				val container = PostgreSQLContainer("postgres:16-alpine")
					.withDatabaseName(dbProperties.databaseName)
					.withUsername(dbProperties.username)
					.withPassword(dbProperties.password)
					.withInitScript(initScript)
					.withCreateContainerCmdModifier { cmd ->
							cmd.withHostConfig(
								HostConfig().withPortBindings(PortBinding(Ports.Binding.bindPort(5432), ExposedPort(5432)))
							)
					}
				container.start()
				return container
		}
}