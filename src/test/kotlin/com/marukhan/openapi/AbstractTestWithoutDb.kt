package com.marukhan.openapi

import com.marukhan.openapi.repository.EmployeeRepository
import com.marukhan.openapi.repository.OrganizationRepository
import com.ninjasquad.springmockk.MockkBean
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc

@SpringBootTest
@EnableAutoConfiguration(exclude = [HibernateJpaAutoConfiguration::class, JpaRepositoriesAutoConfiguration::class])
@ActiveProfiles("test")
@AutoConfigureMockMvc
@TestInstance(PER_CLASS)
abstract class AbstractTestWithoutDb {
		@MockkBean(relaxed = true)
		lateinit var organizationRepository: OrganizationRepository

		@MockkBean(relaxed = true)
		lateinit var employeeRepository: EmployeeRepository

		@Autowired
		lateinit var mockMvc: MockMvc
}