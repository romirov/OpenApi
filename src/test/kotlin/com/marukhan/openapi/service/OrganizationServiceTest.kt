package com.marukhan.openapi.service

import com.marukhan.openapi.AbstractTestWithoutDb
import com.marukhan.openapi.dao.OrganizationEntity
import com.marukhan.openapi.dao.OrganizationTest.organizationEntity1
import com.marukhan.openapi.dao.OrganizationTest.organizationEntity2
import com.marukhan.openapi.dao.OrganizationTest.organizationRq1
import com.marukhan.openapi.dao.OrganizationTest.organizationRq2
import com.marukhan.openapi.dao.OrganizationTest.organizationRs1
import com.marukhan.openapi.dao.OrganizationTest.organizationRs2
import com.marukhan.openapi.mapper.OrganizationMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.util.Optional

class OrganizationServiceTest : AbstractTestWithoutDb() {

	@Autowired
	lateinit var service: OrganizationService

	@MockkBean(relaxed = true)
	lateinit var mapper: OrganizationMapper

	@Test
	fun `test save func`() {
		every { mapper.fromDto(organizationRq1) } returns organizationEntity1
		every { organizationRepository.save(organizationEntity1) } returns organizationEntity1
		every { mapper.toDto(organizationEntity1) } returns organizationRs1
		val result = service.save(organizationRq1)
		Assertions.assertEquals(result.organizationName, organizationRs1.organizationName)
	}

	@Test
	fun `test update func`() {
		val updatedOrganization = organizationEntity2.copy(organizationEntity1.id)
		every { organizationRepository.findById(any()) } returns Optional.of<OrganizationEntity>(organizationEntity1)
		every { organizationRepository.save(updatedOrganization) } returns updatedOrganization
		every { mapper.toDto(updatedOrganization) } returns
			organizationRs1.copy(organizationName = updatedOrganization.organizationName)

		val result = service.update(organizationEntity1.id.toString(), organizationRq2)
		Assertions.assertEquals(result.organizationName, organizationRs2.organizationName)
	}
}