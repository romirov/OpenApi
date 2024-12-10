package com.marukhan.openapi

import com.marukhan.openapi.repository.EmployeeRepository
import com.marukhan.openapi.repository.OrganizationRepository
import com.ninjasquad.springmockk.MockkBean
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@EnableAutoConfiguration(exclude = [HibernateJpaAutoConfiguration::class, JpaRepositoriesAutoConfiguration::class])
@ActiveProfiles("test")
@TestInstance(PER_CLASS)
abstract class AbstractTestWithoutDb{
		@MockkBean(relaxed = true)
		lateinit var organizationRepository: OrganizationRepository

		@MockkBean(relaxed = true)
		lateinit var employeeRepository: EmployeeRepository
}