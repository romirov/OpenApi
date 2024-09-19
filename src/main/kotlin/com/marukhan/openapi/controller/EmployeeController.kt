package com.marukhan.openapi.controller

import com.marukhan.openapi.api.request.EmployeeApi
import com.marukhan.openapi.model.request.Employee
import org.springframework.http.ResponseEntity

class EmployeeController : EmployeeApi {
    override fun addEmployee(organizationId: String, firstName: String, secondName: String, jobTitle: String): ResponseEntity<Employee> {
        TODO("Not yet implemented")
    }

    override fun deleteEmployeeById(organizationId: Long, employeeId: Long): ResponseEntity<Unit> {
        TODO("Not yet implemented")
    }

    override fun deleteEmployees(organizationId: String): ResponseEntity<Unit> {
        TODO("Not yet implemented")
    }

    override fun getEmployeeById(organizationId: Long, employeeId: Long): ResponseEntity<Employee> {
        TODO("Not yet implemented")
    }

    override fun getEmployees(organizationId: String): ResponseEntity<Unit> {
        TODO("Not yet implemented")
    }

    override fun updateEmployeeById(organizationId: String, employeeId: String, firstName: String, secondName: String, jobTitle: String): ResponseEntity<Employee> {
        TODO("Not yet implemented")
    }


}