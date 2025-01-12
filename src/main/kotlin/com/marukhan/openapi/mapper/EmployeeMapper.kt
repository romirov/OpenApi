package com.marukhan.openapi.mapper

import com.marukhan.openapi.dao.EmployeeEntity
import com.marukhan.openapi.dao.OrganizationEntity
import com.marukhan.openapi.model.request.EmployeeRq
import com.marukhan.openapi.model.request.EmployeeRs
import com.marukhan.openapi.repository.OrganizationRepository
import org.springframework.stereotype.Service

@Service
class EmployeeMapper(
	val organizationRepository: OrganizationRepository
) : OpenApiIdMapper() {
	fun fromDto(employee: EmployeeRq, organizationEntity: OrganizationEntity): EmployeeEntity {
		val firstName = employee.firstName
		val lastName = employee.lastName
		val jobTitle = employee.jobTitle

		return EmployeeEntity(
			organization = organizationEntity,
			firstName = firstName,
			lastName = lastName,
			jobTitle = jobTitle
		)
	}

	fun toDto(employee: EmployeeEntity): EmployeeRs = EmployeeRs(
		id = uuidToString(employee.id),
		organizationId = employee.id.toString(),
		firstName = employee.firstName,
		lastName = employee.lastName,
		jobTitle = employee.jobTitle
	)
}