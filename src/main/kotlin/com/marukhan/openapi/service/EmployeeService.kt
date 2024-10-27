package com.marukhan.openapi.service

import com.marukhan.openapi.mapper.EmployeeMapper
import com.marukhan.openapi.model.request.Employee
import com.marukhan.openapi.repository.EmployeeRepository
import java.util.*

open class EmployeeService(
	private val employeeRepository: EmployeeRepository,
	private val employeeMapper: EmployeeMapper
) {
		fun save(employee: Employee): Employee {
				val employeeEntity = employeeMapper.fromDto(employee)
				val employeeEntityFromDb = employeeRepository.save(employeeEntity)
				return employeeMapper.toDto(employeeEntityFromDb)
		}

		fun update(employee: Employee): Employee {
				val employeeOld = employeeRepository.findById(UUID.fromString(employee.id)).orElseThrow()
				val emplEntityNew = employeeMapper.fromDto(employee).copy(employeeOld.id)
				val employeeEntityFromDb = employeeRepository.save(emplEntityNew)
				return employeeMapper.toDto(employeeEntityFromDb)
		}

		fun deleteById(employeeId: String) =
			employeeRepository.deleteById(employeeMapper.stringToUuid(employeeId))

		fun deleteAll() = employeeRepository.deleteAll()

		fun findAll() = employeeRepository.findAll().map { employeeMapper.toDto(it) }
}
