package com.marukhan.openapi.service

import com.marukhan.openapi.AbstractTestWithoutDb
import com.marukhan.openapi.dao.OrganizationTest
import com.marukhan.openapi.mapper.OrganizationMapper
import com.marukhan.openapi.model.request.Organization
import com.marukhan.openapi.repository.OrganizationRepository
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class OrganizationServiceTest : AbstractTestWithoutDb() {

		@Autowired
		lateinit var organizationService: OrganizationService

		@MockkBean(relaxed = true)
		lateinit var repository: OrganizationRepository

		@MockkBean(relaxed = true)
		lateinit var mapper: OrganizationMapper

		@Test
		fun `test save func`() {
				val org = Organization(
					organizationName = OrganizationTest.organization.organizationName,
					employees = emptyList()
				)
				val orgEntity = OrganizationTest.organization
				every { mapper.fromDto(org) } returns orgEntity
				every { repository.save(orgEntity) } returns orgEntity.copy(id = OrganizationTest.organization.id,)
				val result = organizationService.save(org)
				Assertions.assertEquals(result.organizationName, org.organizationName)
		}

//		private fun mockkMapFromDto() {
//				every { mapper.fromDto(OrganizationTest.organization) } returns
//		}

}