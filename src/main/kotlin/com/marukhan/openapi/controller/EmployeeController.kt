package com.marukhan.openapi.controller

import com.marukhan.openapi.api.request.EmployeeApi
import com.marukhan.openapi.model.request.Employee
import com.marukhan.openapi.service.EmployeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class EmployeeController : EmployeeApi {
    @Autowired
    lateinit var employeeService: EmployeeService

    override fun addEmployee(organizationId: String, firstName: String, secondName: String, jobTitle: String): ResponseEntity<Employee> =
            ResponseEntity.ok(employeeService.save(Employee(organizationId, firstName, secondName, jobTitle)))

    override fun deleteEmployee(organizationId: String, employeeId: String): ResponseEntity<Unit> {
        TODO("Not yet implemented")
    }

    override fun updateEmployeeById(organizationId: String, employeeId: String, firstName: String, secondName: String, jobTitle: String): ResponseEntity<Employee> =
            ResponseEntity.ok(employeeService.update(Employee(employeeId, organizationId, firstName, secondName, jobTitle)))

    override fun deleteEmployees(organizationId: String): ResponseEntity<Unit> {
        employeeService.deleteAll()
        return ResponseEntity.ok().build()
    }

    override fun getEmployee(organizationId: String, employeeId: String): ResponseEntity<List<Employee>> {
        TODO("Not yet implemented")
    }

    override fun getEmployees(organizationId: String): ResponseEntity<List<Employee>> =
            ResponseEntity.ok(employeeService.findAll())
}