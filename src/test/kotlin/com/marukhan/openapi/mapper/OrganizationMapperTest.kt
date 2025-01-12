package com.marukhan.openapi.mapper

import com.marukhan.openapi.AbstractTestWithoutDb
import com.marukhan.openapi.dao.OrganizationTest.organizationEntity1
import com.marukhan.openapi.dao.OrganizationTest.organizationRq1
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class OrganizationMapperTest : AbstractTestWithoutDb() {
	@Autowired
	lateinit var mapper: OrganizationMapper

	@Test
	fun `test toDto`() {
		val res = mapper.toDto(organizationEntity1)
		Assertions.assertEquals(res.organizationName, organizationEntity1.organizationName)
	}

	@Test
	fun `test fromDto`() {
		val res = mapper.fromDto(organizationRq1)
		Assertions.assertEquals(res.organizationName, organizationEntity1.organizationName)
	}
}