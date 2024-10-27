package com.marukhan.openapi.mapper

import com.marukhan.openapi.dao.OrganizationEntity
import com.marukhan.openapi.model.request.Organization

class OrganizationMapper : OpenApiIdMapper() {
		fun fromDto(organization: Organization): OrganizationEntity {
				val organizationName = organization.organizationName ?: error("Organization name can't be null")
				return OrganizationEntity(
					organizationName = organizationName,
					employees = emptyList()
				)
		}

		fun toDto(organizationEntity: OrganizationEntity): Organization = Organization(
			id = uuidToString(organizationEntity.id),
			organizationName = organizationEntity.organizationName
		)
}