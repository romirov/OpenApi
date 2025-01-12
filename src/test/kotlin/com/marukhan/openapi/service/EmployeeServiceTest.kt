package com.marukhan.openapi.service

import com.marukhan.openapi.AbstractTestWithoutDb
import com.marukhan.openapi.dao.EmployeesTest.employeeEntity1
import com.marukhan.openapi.dao.EmployeesTest.employeeEntity2
import com.marukhan.openapi.dao.EmployeesTest.employeeEntityId1
import com.marukhan.openapi.dao.EmployeesTest.employeeRq1
import com.marukhan.openapi.dao.EmployeesTest.employeeRq2
import com.marukhan.openapi.dao.EmployeesTest.employeeRs1
import com.marukhan.openapi.dao.EmployeesTest.employeeRs2
import com.marukhan.openapi.dao.OrganizationTest.organizationEntity1
import com.marukhan.openapi.mapper.EmployeeMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.util.Optional

class EmployeeServiceTest : AbstractTestWithoutDb() {
	@Autowired
	lateinit var service: EmployeeService

	@MockkBean(relaxed = true)
	lateinit var mapper: EmployeeMapper

	@Test
	fun `test save func`() {
		every { organizationRepository.findById(any()) } returns Optional.ofNullable(organizationEntity1)
		every { mapper.fromDto(any(), any()) } returns employeeEntity1
		every { mapper.toDto(any()) } returns employeeRs1
		val result = service.save(employeeRq1)
		Assertions.assertEquals(result.firstName, employeeRs1.firstName)
		Assertions.assertEquals(result.lastName, employeeRs1.lastName)
	}

	@Test
	fun `test update func`() {
		every { employeeRepository.findById(any()) } returns Optional.ofNullable(employeeEntity2)
		every { organizationRepository.findById(any()) } returns Optional.ofNullable(organizationEntity1)
		every { mapper.fromDto(any(), any()) } returns employeeEntity2
		every { mapper.toDto(any()) } returns employeeRs2
		val result = service.update(employeeEntityId1.toString(), employeeRq2)
		Assertions.assertEquals(result.firstName, employeeRs2.firstName)
		Assertions.assertEquals(result.lastName, employeeRs2.lastName)
	}
}