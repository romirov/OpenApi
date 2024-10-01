package com.marukhan.openapi.mapper

import com.marukhan.openapi.dao.OrganizationEntity
import com.marukhan.openapi.model.request.Organization

class OrganizationMapper : OpenApiIdMapper() {
    fun fromDto(organization: Organization): OrganizationEntity {
        requireNotNull(organization.id)
        requireNotNull(organization.organizationName)
        return OrganizationEntity(
            id = stringToUuid(organization.id),
            organizationName = organization.organizationName
        )
    }

    fun toDto(organizationEntity: OrganizationEntity): Organization = Organization(
        id = uuidToString(organizationEntity.id),
        organizationName = organizationEntity.organizationName
    )
}