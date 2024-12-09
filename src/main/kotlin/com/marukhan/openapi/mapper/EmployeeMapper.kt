package com.marukhan.openapi.mapper

import com.marukhan.openapi.dao.EmployeeEntity
import com.marukhan.openapi.dao.OrganizationEntity
import com.marukhan.openapi.model.request.Employee
import com.marukhan.openapi.repository.OrganizationRepository
import org.springframework.stereotype.Service

@Service
class EmployeeMapper(
	val organizationRepository: OrganizationRepository
) : OpenApiIdMapper() {
		fun fromDto(employee: Employee, organizationEntity: OrganizationEntity?): EmployeeEntity {
				val orgId = organizationEntity?.id ?: error("organization id can't be null")
				val firstName = employee.firstName ?: error("firstName can't be null")
				val lastName = employee.lastName ?: error("lastName can't be null")
				val jobTitle = employee.jobTitle ?: error("jobTitle can't be null")

				return EmployeeEntity(
					organization = organizationEntity,
					firstName = firstName,
					lastName = lastName,
					jobTitle = jobTitle
				)
		}

		fun toDto(employee: EmployeeEntity): Employee = Employee(
			id = uuidToString(employee.id),
			firstName = employee.firstName,
			lastName = employee.lastName,
			jobTitle = employee.jobTitle
		)
}