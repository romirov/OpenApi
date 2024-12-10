package com.marukhan.openapi.service

import com.marukhan.openapi.AbstractTestWithoutDb
import com.marukhan.openapi.dao.EmployeesTest
import com.marukhan.openapi.dao.OrganizationTest
import com.marukhan.openapi.mapper.EmployeeMapper
import com.marukhan.openapi.model.request.Employee
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.util.Optional
import java.util.UUID

class EmployeeServiceTest : AbstractTestWithoutDb() {
		@Autowired
		lateinit var service: EmployeeService

		@MockkBean(relaxed = true)
		lateinit var mapper: EmployeeMapper

		@Test
		fun `test save func`() {
				every { organizationRepository.findById(any()) } returns Optional.ofNullable(OrganizationTest.organization)
				every { mapper.fromDto(any(), any()) } returns EmployeesTest.employee1
				every { mapper.toDto(any()) } returns employee
				val result = service.save(employee)
				Assertions.assertEquals(result.firstName, EmployeesTest.employee1.firstName)
				Assertions.assertEquals(result.lastName, EmployeesTest.employee1.lastName)
		}

		@Test
		fun `test update func`() {
				every { employeeRepository.findById(any()) } returns Optional.ofNullable(EmployeesTest.employee2)
				every { organizationRepository.findById(any()) } returns Optional.ofNullable(OrganizationTest.organization)
				every { mapper.fromDto(any(), any()) } returns EmployeesTest.employee2
				every { mapper.toDto(any()) } returns employee2
				val result = service.update(employee2)
				Assertions.assertEquals(result.firstName, EmployeesTest.employee2.firstName)
				Assertions.assertEquals(result.lastName, EmployeesTest.employee2.lastName)
		}

		private companion object {
				val employee = Employee(
					id = UUID.randomUUID().toString(),
					organizationId = EmployeesTest.employee1.organization.id.toString(),
					firstName = EmployeesTest.employee1.firstName,
					lastName = EmployeesTest.employee1.lastName,
					jobTitle = EmployeesTest.employee1.jobTitle
				)

				val employee2 = Employee(
					id = UUID.randomUUID().toString(),
					organizationId = EmployeesTest.employee2.organization.id.toString(),
					firstName = EmployeesTest.employee2.firstName,
					lastName = EmployeesTest.employee2.lastName,
					jobTitle = EmployeesTest.employee2.jobTitle
				)
		}
}