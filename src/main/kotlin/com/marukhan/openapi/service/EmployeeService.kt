package com.marukhan.openapi.service

import com.marukhan.openapi.mapper.EmployeeMapper
import com.marukhan.openapi.model.request.Employee
import com.marukhan.openapi.repository.EmployeeRepository

class EmployeeService(
        private val employeeRepository: EmployeeRepository,
        private val employeeMapper: EmployeeMapper
) {
    fun save(employee: Employee): Employee {
        val employeeEntity = employeeMapper.fromDto(employee)
        val employeeEntityFromDb = employeeRepository.save(employeeEntity)
        return employeeMapper.toDto(employeeEntityFromDb)
    }

    fun update(employee: Employee): Employee {
        val employeeEntity = employeeMapper.fromDto(employee)
        val employeeEntityFromDb = employeeRepository.updateById(employeeEntity)
        return employeeMapper.toDto(employeeEntityFromDb)
    }

    fun deleteByEmployeeIdAndOrganizationId(organizationId: String, employeeId: String) =
            employeeRepository.deleteByEmployeeIdAndOrganizationId(
                    employeeMapper.stringToUuid(employeeId),
                    employeeMapper.stringToUuid(organizationId)
            )

    fun deleteAll() = employeeRepository.deleteAll()

    fun findAll() = employeeRepository.findAll().map { employeeMapper.toDto(it) }

    fun findByEmployeeIdAndOrganizationId(employeeId: String, organizationId: String) =
            employeeRepository.findByEmployeeIdAndOrganizationId(
                    employeeMapper.stringToUuid(employeeId),
                    employeeMapper.stringToUuid(organizationId)
            ).let { employeeMapper.toDto(it.orElseThrow()) }
}
