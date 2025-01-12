package com.marukhan.openapi.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.marukhan.openapi.AbstractTestWithoutDb
import com.marukhan.openapi.model.request.EmployeeRq
import com.marukhan.openapi.model.request.EmployeeRs
import com.marukhan.openapi.service.EmployeeService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.coVerify
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.util.UUID

class EmployeeControllerTest : AbstractTestWithoutDb() {
	@MockkBean(relaxed = true)
	lateinit var employeeService: EmployeeService

	@Test
	fun `add Employee test`() {
		every { employeeService.save(any()) } returns employee
		mockMvc.perform(
			MockMvcRequestBuilders.post("/api/v1/employee")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jacksonObjectMapper().writeValueAsString(createEmployeeRq))
		)
			.andExpect(MockMvcResultMatchers.content().string(jacksonObjectMapper().writeValueAsString(employee)))
			.andReturn()

		coVerify {
			employeeService.save(any())
		}
	}

	private companion object {
		val employeeId = UUID.randomUUID().toString()
		val organizationId = UUID.randomUUID().toString()
		val createEmployeeRq =
			EmployeeRq(
				organizationId,
				"Test",
				"Test",
				"Test"
			)
		val employee = EmployeeRs(
			id = employeeId,
			organizationId = organizationId,
			firstName = "Test",
			lastName = "Test",
			jobTitle = "Test"
		)
	}
}