package com.marukhan.openapi.mapper

import com.marukhan.openapi.AbstractTestWithoutDb
import com.marukhan.openapi.dao.EmployeesTest.employeeEntity1
import com.marukhan.openapi.dao.EmployeesTest.employeeRq1
import com.marukhan.openapi.dao.EmployeesTest.employeeRs1
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class EmployeeMapperTest : AbstractTestWithoutDb() {
	@Autowired
	lateinit var mapper: EmployeeMapper

	@Test
	fun `test toDto`() {
		val res = mapper.toDto(employeeEntity1)
		Assertions.assertEquals(res.firstName, employeeRs1.firstName)
		Assertions.assertEquals(res.lastName, employeeRs1.lastName)
	}

	@Test
	fun `test fromDto`() {
		val res = mapper.fromDto(employeeRq1, employeeEntity1.organization)
		Assertions.assertEquals(res.firstName, employeeEntity1.firstName)
		Assertions.assertEquals(res.lastName, employeeEntity1.lastName)
	}
}