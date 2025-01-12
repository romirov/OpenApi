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
	fun fromDto(employeeRq: EmployeeRq, organizationEntity: OrganizationEntity): EmployeeEntity {
		val firstName = employeeRq.firstName
		val lastName = employeeRq.lastName
		val jobTitle = employeeRq.jobTitle

		return EmployeeEntity(
			organization = organizationEntity,
			firstName = firstName,
			lastName = lastName,
			jobTitle = jobTitle
		)
	}

	fun toDto(employeeEntity: EmployeeEntity): EmployeeRs = with(employeeEntity) {
		EmployeeRs(
			id = uuidToString(id),
			organizationId = id.toString(),
			firstName = firstName,
			lastName = lastName,
			jobTitle = jobTitle
		)
	}
}