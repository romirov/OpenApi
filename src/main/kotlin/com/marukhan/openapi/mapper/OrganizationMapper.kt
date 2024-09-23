package com.marukhan.openapi.mapper

import com.marukhan.openapi.dao.OrganizationEntity
import com.marukhan.openapi.model.request.Organization
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper
abstract class OrganizationMapper: OpenApiIdMapper() {
    @Mapping(target = "organizationName", source = "organizationName")
    abstract fun fromDto(organization: Organization): OrganizationEntity

    @Mappings(
            Mapping(target = "id", source = "id", qualifiedByName = ["uuidToString"]),
            Mapping(target = "organizationName", source = "organizationName")
    )
    abstract fun toDto(organizationEntity: OrganizationEntity): Organization
}