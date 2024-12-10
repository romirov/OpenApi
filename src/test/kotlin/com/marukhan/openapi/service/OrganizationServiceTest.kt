package com.marukhan.openapi.service

import com.marukhan.openapi.AbstractTestWithoutDb
import com.marukhan.openapi.dao.OrganizationTest
import com.marukhan.openapi.mapper.OrganizationMapper
import com.marukhan.openapi.model.request.Organization
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class OrganizationServiceTest : AbstractTestWithoutDb() {

		@Autowired
		lateinit var service: OrganizationService

		@MockkBean(relaxed = true)
		lateinit var mapper: OrganizationMapper

		@Test
		fun `test save func`() {
				every { mapper.fromDto(org1) } returns orgEntity1
				every { organizationRepository.save(orgEntity1) } returns orgEntity1.copy(id = OrganizationTest.organization.id)
				every { mapper.toDto(orgFromDb1) } returns org1.copy(id = OrganizationTest.organization.id.toString())
				val result = service.save(org1)
				Assertions.assertEquals(result.organizationName, org1.organizationName)
		}

		@Test
		fun `test update func`() {
				every { mapper.fromDto(org2) } returns orgEntity2
				every { organizationRepository.save(orgEntity2) } returns
					orgEntity2.copy(id = OrganizationTest.organization.id)
				every { organizationRepository.existsById(any()) } returns true
				every { mapper.toDto(orgFromDb2) } returns org2.copy(id = OrganizationTest.organization.id.toString())
				val result = service.update(org2)
				Assertions.assertEquals(result.organizationName, org2.organizationName)
		}

		private companion object {
				val org1 = Organization(
					organizationName = OrganizationTest.organization.organizationName,
					employees = emptyList()
				)
				val orgEntity1 = OrganizationTest.organization
				val orgFromDb1 = orgEntity1.copy(id = OrganizationTest.organization.id)

				val org2 = org1.copy(organizationName = "newName")
				val orgEntity2 = orgEntity1.copy(organizationName = "newName")
				val orgFromDb2 = orgEntity2.copy(id = OrganizationTest.organization.id)
		}
}