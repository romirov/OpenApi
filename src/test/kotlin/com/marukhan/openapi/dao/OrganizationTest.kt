package com.marukhan.openapi.dao

import com.marukhan.openapi.model.request.OrganizationRq
import com.marukhan.openapi.model.request.OrganizationRs
import java.util.UUID

object OrganizationTest {
	val organizationId1 = UUID.fromString("b749d000-1111-4bb7-8235-3e47779df243")
	val organizationId2 = UUID.fromString("b749d000-2222-4bb7-8235-3e47779df243")

	val organizationRq1 = OrganizationRq(organizationName = "HHCorp1111")
	val organizationRq2 = OrganizationRq(organizationName = "HHCorp2222")

	val organizationEntity1 = OrganizationEntity(id = organizationId1, organizationName = "HHCorp1111", employees = emptySet())
	val organizationEntity2 = OrganizationEntity(id = organizationId2, organizationName = "HHCorp2222", employees = emptySet())

	val organizationRs1 = OrganizationRs(id = organizationId1.toString(), organizationName = "HHCorp1111", employees = emptyList())
	val organizationRs2 = OrganizationRs(id = organizationId2.toString(), organizationName = "HHCorp2222", employees = emptyList())
}