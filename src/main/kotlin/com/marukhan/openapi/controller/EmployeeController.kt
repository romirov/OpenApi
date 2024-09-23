package com.marukhan.openapi.controller

import com.marukhan.openapi.api.request.EmployeeApi
import com.marukhan.openapi.model.request.Employee
import com.marukhan.openapi.service.EmployeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity

class EmployeeController : EmployeeApi {
    @Autowired
    lateinit var employeeService: EmployeeService

    override fun addEmployee(organizationId: String, firstName: String, secondName: String, jobTitle: String): ResponseEntity<Employee> =
            ResponseEntity.ok(employeeService.save(Employee(organizationId, firstName, secondName, jobTitle)))

    override fun updateEmployeeById(organizationId: String, employeeId: String, firstName: String, secondName: String, jobTitle: String): ResponseEntity<Employee> =
            ResponseEntity.ok(employeeService.update(Employee(employeeId, organizationId, firstName, secondName, jobTitle)))

    override fun deleteEmployeeById(organizationId: String, employeeId: String): ResponseEntity<Unit> {
        employeeService.deleteByEmployeeIdAndOrganizationId(employeeId, organizationId)
        return ResponseEntity.ok().build()
    }

    override fun deleteEmployees(organizationId: String): ResponseEntity<Unit> {
        employeeService.deleteAll()
        return ResponseEntity.ok().build()
    }

    override fun getEmployeeById(organizationId: String, employeeId: String): ResponseEntity<Employee> =
            ResponseEntity.ok(employeeService.findByEmployeeIdAndOrganizationId(employeeId, organizationId))

    override fun getEmployees(organizationId: String): ResponseEntity<List<Employee>> =
            ResponseEntity.ok(employeeService.findAll())
}