package com.marukhan.openapi.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.marukhan.openapi.AbstractTestWithoutDb
import com.marukhan.openapi.dao.OrganizationEntity
import com.marukhan.openapi.model.request.Employee
import io.mockk.every
import okhttp3.mockwebserver.MockResponse
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.util.Optional
import java.util.UUID

class EmployeeControllerTest : AbstractTestWithoutDb() {
		@Autowired
		lateinit var employeeController: EmployeeController

		@Test
		fun `add Employee test`() {
				every { organizationRepository.findById(any()) } returns organization
				mockWebServer.enqueue(
					MockResponse()
						.setResponseCode(200)
						.setBody(
							jacksonObjectMapper().writeValueAsString(employee)
						)
				)
				val result = employeeController.addEmployee(
					employee.organizationId, employee.firstName, employee.lastName, employee.jobTitle
				)
				val rq = mockWebServer.takeRequest()
				Assertions.assertEquals(result.body?.firstName, employee.firstName)
				Assertions.assertEquals(result.body?.lastName, employee.lastName)
		}

		private companion object {
				val employeeId = UUID.randomUUID().toString()
				val organizationId = UUID.randomUUID().toString()
				val employee = Employee(
					id = employeeId,
					organizationId = organizationId,
					firstName = "Test",
					lastName = "Test",
					jobTitle = "Test"
				)
				val organization: Optional<OrganizationEntity> = Optional.of<OrganizationEntity>(
					OrganizationEntity(
						id = UUID.fromString(organizationId),
						organizationName = "Test",
						employees = emptySet()
					)
				)
		}
}