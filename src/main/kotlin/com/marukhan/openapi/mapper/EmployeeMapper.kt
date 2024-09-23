package com.marukhan.openapi.mapper

import com.marukhan.openapi.dao.EmployeeEntity
import com.marukhan.openapi.model.request.Employee
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper
abstract class EmployeeMapper : OpenApiIdMapper() {
    @Mappings(
            Mapping(target = "id", source = "id", qualifiedByName = ["stringToUuid"]),
            Mapping(target = "organizationId", source = "organizationId", qualifiedByName = ["stringToUuid"]),
            Mapping(target = "firstName", source = "firstName"),
            Mapping(target = "secondName", source = "secondName"),
            Mapping(target = "jobTitle", source = "jobTitle")
    )
    abstract fun fromDto(employee: Employee): EmployeeEntity

    @Mappings(
            Mapping(target = "id", source = "id", qualifiedByName = ["uuidToString"]),
            Mapping(target = "organizationId", source = "organizationId", qualifiedByName = ["uuidToString"]),
            Mapping(target = "firstName", source = "firstName"),
            Mapping(target = "secondName", source = "secondName"),
            Mapping(target = "jobTitle", source = "jobTitle")
    )
    abstract fun toDto(employee: EmployeeEntity): Employee
}