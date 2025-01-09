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

		override fun deleteEmployee(employeeId: String): ResponseEntity<Unit> =
			ResponseEntity.ok(employeeService.deleteById(employeeId))

		override fun updateEmployeeById(employeeId: String, organizationId: String, firstName: String, secondName: String, jobTitle: String): ResponseEntity<Employee> =
			ResponseEntity.ok(employeeService.update(Employee(employeeId, organizationId, firstName, secondName, jobTitle)))

		override fun deleteEmployees(): ResponseEntity<Unit> =
			ResponseEntity.ok(employeeService.deleteAll())

		override fun getEmployee(employeeId: String): ResponseEntity<Employee> =
			ResponseEntity.ok(employeeService.findById(employeeId))

		override fun getEmployees(): ResponseEntity<List<Employee>> =
			ResponseEntity.ok(employeeService.findAll())
}