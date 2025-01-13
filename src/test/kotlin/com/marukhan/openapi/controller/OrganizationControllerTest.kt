package com.marukhan.openapi.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.marukhan.openapi.AbstractTestWithoutDb
import com.marukhan.openapi.dao.OrganizationTest.organizationRq1
import com.marukhan.openapi.dao.OrganizationTest.organizationRs1
import com.marukhan.openapi.service.OrganizationService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.coEvery
import io.mockk.coVerify
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

class OrganizationControllerTest : AbstractTestWithoutDb() {
	@MockkBean(relaxed = true)
	lateinit var organizationService: OrganizationService

	@Test
	fun `add Organizations test`() {
		coEvery { organizationService.save(any()) } returns organizationRs1
		mockMvc.perform(
			MockMvcRequestBuilders.post("/api/v1/organization")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jacksonObjectMapper().writeValueAsString(organizationRq1))
		)
			.andExpect(MockMvcResultMatchers.content().string(jacksonObjectMapper().writeValueAsString(organizationRs1)))
			.andReturn()

		coVerify {
			organizationService.save(any())
		}
	}

	@Test
	fun `update Organization test`() {
		coEvery { organizationService.update(any(), any()) } returns organizationRs1
		mockMvc.perform(
			MockMvcRequestBuilders.put("/api/v1/organization/{organizationId}", "1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jacksonObjectMapper().writeValueAsString(organizationRq1))
		)
			.andExpect(MockMvcResultMatchers.content().string(jacksonObjectMapper().writeValueAsString(organizationRs1)))
			.andReturn()

		coVerify {
			organizationService.update(any(), any())
		}
	}

	@Test
	fun `get Organization test`() {
		coEvery { organizationService.findById(any()) } returns organizationRs1
		mockMvc.perform(
			MockMvcRequestBuilders.get("/api/v1/organization/{organizationId}", "1")
		)
			.andExpect(MockMvcResultMatchers.status().isOk)
			.andReturn()

		coVerify {
			organizationService.findById(any())
		}
	}

	@Test
	fun `delete Organization test`() {
		coEvery { organizationService.deleteById(any()) } returns Unit
		mockMvc.perform(
			MockMvcRequestBuilders.delete("/api/v1/organization/{organizationId}", "1")
		)
			.andExpect(MockMvcResultMatchers.status().isOk)
			.andReturn()

		coVerify {
			organizationService.deleteById(any())
		}
	}

	@Test
	fun `delete Organizations test`() {
		coEvery { organizationService.deleteAll() } returns Unit
		mockMvc.perform(
			MockMvcRequestBuilders.delete("/api/v1/organization")
		)
			.andExpect(MockMvcResultMatchers.status().isOk)
			.andReturn()

		coVerify {
			organizationService.deleteAll()
		}
	}

	@Test
	fun `get Employees test`() {
		coEvery { organizationService.findAll() } returns listOf(organizationRs1)
		mockMvc.perform(
			MockMvcRequestBuilders.get("/api/v1/organization")
		)
			.andExpect(MockMvcResultMatchers.content().string(jacksonObjectMapper().writeValueAsString(listOf(organizationRs1))))
			.andReturn()

		coVerify {
			organizationService.findAll()
		}
	}
}