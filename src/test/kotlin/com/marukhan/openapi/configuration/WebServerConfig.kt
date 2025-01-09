package com.marukhan.openapi.configuration

import okhttp3.mockwebserver.MockWebServer
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean

@TestConfiguration
class WebServerConfig {
		@Bean
		fun webServer(): MockWebServer {
				return MockWebServer()
		}
}