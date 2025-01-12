package com.marukhan.openapi.mapper

import com.marukhan.openapi.dao.OrganizationEntity
import com.marukhan.openapi.model.request.OrganizationRq
import com.marukhan.openapi.model.request.OrganizationRs
import org.springframework.stereotype.Service

@Service
class OrganizationMapper : OpenApiIdMapper() {
	fun fromDto(organization: OrganizationRq): OrganizationEntity {
		val organizationName = organization.organizationName
		return OrganizationEntity(
			organizationName = organizationName,
			employees = emptySet()
		)
	}

	fun toDto(organizationEntity: OrganizationEntity): OrganizationRs = OrganizationRs(
		id = uuidToString(organizationEntity.id),
		organizationName = organizationEntity.organizationName
	)
}