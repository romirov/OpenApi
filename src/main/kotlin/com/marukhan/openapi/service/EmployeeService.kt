package com.marukhan.openapi.service

import com.marukhan.openapi.mapper.EmployeeMapper
import com.marukhan.openapi.model.request.Employee
import com.marukhan.openapi.repository.EmployeeRepository
import com.marukhan.openapi.repository.OrganizationRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class EmployeeService(
	private val employeeRepository: EmployeeRepository,
	private val organizationRepository: OrganizationRepository,
	private val employeeMapper: EmployeeMapper
) {
		fun save(employee: Employee): Employee {
				val organization = organizationRepository.findById(
					UUID.fromString(employee.organizationId)
				).orElseThrow()
				val employeeEntity = employeeMapper.fromDto(employee, organization)
				val employeeEntityFromDb = employeeRepository.save(employeeEntity)
				return employeeMapper.toDto(employeeEntityFromDb)
		}

		fun update(employee: Employee): Employee {
				val employeeOld = employeeRepository.findById(
					UUID.fromString(employee.id)
				).orElseThrow()
				val organization = organizationRepository.findById(
					UUID.fromString(employee.organizationId)
				).orElseThrow()
				val empEntityNew = employeeMapper.fromDto(employee, organization).copy(employeeOld.id)
				val employeeEntityFromDb = employeeRepository.save(empEntityNew)
				return employeeMapper.toDto(employeeEntityFromDb)
		}

		fun deleteById(employeeId: String) =
			employeeRepository.deleteById(employeeMapper.stringToUuid(employeeId))

		fun deleteAll() = employeeRepository.deleteAll()

		fun findAll() = employeeRepository.findAll().map { employeeMapper.toDto(it) }

		fun findById(employeeId: String) =
			employeeMapper.toDto(employeeRepository.findById(UUID.fromString(employeeId)).orElseThrow())
}
