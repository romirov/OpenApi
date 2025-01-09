package com.marukhan.openapi.mapper

import com.marukhan.openapi.AbstractTestWithoutDb
import com.marukhan.openapi.dao.OrganizationTest
import com.marukhan.openapi.model.request.Organization
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.util.UUID

class OrganizationMapperTest : AbstractTestWithoutDb() {
		@Autowired
		lateinit var mapper: OrganizationMapper

		@Test
		fun `test toDto`() {
				val res = mapper.toDto(OrganizationTest.organization)
				Assertions.assertEquals(res.organizationName, OrganizationTest.organization.organizationName)
		}

		@Test
		fun `test fromDto`() {
				val organization: Organization = Organization(id = UUID.randomUUID().toString(), organizationName = "HHCorp")
				val res = mapper.fromDto(organization)
				Assertions.assertEquals(res.organizationName, OrganizationTest.organization.organizationName)
		}
}