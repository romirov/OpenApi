package com.marukhan.openapi.mapper

import com.marukhan.openapi.dao.EmployeeEntity
import com.marukhan.openapi.model.request.Employee

class EmployeeMapper : OpenApiIdMapper() {
    fun fromDto(employee: Employee): EmployeeEntity {
        requireNotNull(employee.id)
        requireNotNull(employee.organizationId)
        requireNotNull(employee.firstName)
        requireNotNull(employee.lastName)
        requireNotNull(employee.jobTitle)
        return EmployeeEntity(
            id = stringToUuid(employee.id),
            organizationId = stringToUuid(employee.organizationId),
            firstName = employee.firstName,
            lastName = employee.lastName,
            jobTitle = employee.jobTitle
        )
    }

    fun toDto(employee: EmployeeEntity): Employee = Employee(
        id = uuidToString(employee.id),
        organizationId = uuidToString(employee.organizationId),
        firstName = employee.firstName,
        lastName = employee.lastName,
        jobTitle = employee.jobTitle
    )
}