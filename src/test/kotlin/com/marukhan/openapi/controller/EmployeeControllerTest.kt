package com.marukhan.openapi.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.marukhan.openapi.AbstractTestWithoutDb
import com.marukhan.openapi.dao.EmployeesTest.employeeRq1
import com.marukhan.openapi.dao.EmployeesTest.employeeRs1
import com.marukhan.openapi.service.EmployeeService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.coEvery
import io.mockk.coVerify
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

class EmployeeControllerTest : AbstractTestWithoutDb() {
	@MockkBean(relaxed = true)
	lateinit var employeeService: EmployeeService

	@Test
	fun `add Employee test`() {
		coEvery { employeeService.save(any()) } returns employeeRs1
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

	@Test
	fun `update Employee test`() {
		coEvery { employeeService.update(any(), any()) } returns employeeRs1
		mockMvc.perform(
			MockMvcRequestBuilders.put("/api/v1/employee/{employeeId}", "1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jacksonObjectMapper().writeValueAsString(employeeRq1))
		)
			.andExpect(MockMvcResultMatchers.content().string(jacksonObjectMapper().writeValueAsString(employeeRs1)))
			.andReturn()

		coVerify {
			employeeService.update(any(), any())
		}
	}

	@Test
	fun `get Employee test`() {
		coEvery { employeeService.findById(any()) } returns employeeRs1
		mockMvc.perform(
			MockMvcRequestBuilders.get("/api/v1/employee/{employeeId}", "1")
		)
			.andExpect(MockMvcResultMatchers.content().string(jacksonObjectMapper().writeValueAsString(employeeRs1)))
			.andReturn()

		coVerify {
			employeeService.findById(any())
		}
	}

	@Test
	fun `delete Employee test`() {
		coEvery { employeeService.deleteById(any()) } returns Unit
		mockMvc.perform(
			MockMvcRequestBuilders.delete("/api/v1/employee/{employeeId}", "1")
		)
			.andExpect(MockMvcResultMatchers.status().isOk)
			.andReturn()

		coVerify {
			employeeService.deleteById(any())
		}
	}

	@Test
	fun `delete Employees test`() {
		coEvery { employeeService.deleteAll() } returns Unit
		mockMvc.perform(
			MockMvcRequestBuilders.delete("/api/v1/employee")
		)
			.andExpect(MockMvcResultMatchers.status().isOk)
			.andReturn()

		coVerify {
			employeeService.deleteAll()
		}
	}

	@Test
	fun `get Employees test`() {
		coEvery { employeeService.findAll() } returns listOf(employeeRs1)
		mockMvc.perform(
			MockMvcRequestBuilders.get("/api/v1/employee")
		)
			.andExpect(MockMvcResultMatchers.content().string(jacksonObjectMapper().writeValueAsString(listOf(employeeRs1))))
			.andReturn()

		coVerify {
			employeeService.findAll()
		}
	}
}