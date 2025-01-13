package com.marukhan.openapi.controller

import com.marukhan.openapi.api.request.EmployeeApi
import com.marukhan.openapi.model.request.EmployeeRq
import com.marukhan.openapi.model.request.EmployeeRs
import com.marukhan.openapi.service.EmployeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class EmployeeController : EmployeeApi {
	@Autowired
	lateinit var employeeService: EmployeeService

	override fun addEmployee(employeeRq: EmployeeRq): ResponseEntity<EmployeeRs> =
		ResponseEntity.ok(
			employeeService.save(employeeRq)
		)

	override fun updateEmployeeById(employeeId: String, employeeRq: EmployeeRq): ResponseEntity<EmployeeRs> =
		ResponseEntity.ok(
			employeeService.update(employeeId, employeeRq)
		)

	override fun getEmployeeById(employeeId: String): ResponseEntity<EmployeeRs> =
		ResponseEntity.ok(
			employeeService.findById(employeeId)
		)

	override fun deleteEmployeeById(employeeId: String): ResponseEntity<Unit> =
		ResponseEntity.ok(
			employeeService.deleteById(employeeId)
		)

	override fun deleteEmployees(): ResponseEntity<Unit> =
		ResponseEntity.ok(
			employeeService.deleteAll()
		)

	override fun getEmployees(): ResponseEntity<List<EmployeeRs>> =
		ResponseEntity.ok(
			employeeService.findAll()
		)
}