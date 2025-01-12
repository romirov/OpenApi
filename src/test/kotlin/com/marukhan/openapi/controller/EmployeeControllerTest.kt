package com.marukhan.openapi.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.marukhan.openapi.AbstractTestWithoutDb
import com.marukhan.openapi.dao.EmployeesTest.employeeRq1
import com.marukhan.openapi.dao.EmployeesTest.employeeRs1
import com.marukhan.openapi.service.EmployeeService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.coVerify
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

class EmployeeControllerTest : AbstractTestWithoutDb() {
	@MockkBean(relaxed = true)
	lateinit var employeeService: EmployeeService

	@Test
	fun `add Employee test`() {
		every { employeeService.save(any()) } returns employeeRs1
		mockMvc.perform(
			MockMvcRequestBuilders.post("/api/v1/employee")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jacksonObjectMapper().writeValueAsString(employeeRq1))
		)
			.andExpect(MockMvcResultMatchers.content().string(jacksonObjectMapper().writeValueAsString(employeeRs1)))
			.andReturn()

		coVerify {
			employeeService.save(any())
		}
	}
}