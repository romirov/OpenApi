package com.marukhan.openapi.mapper

import com.marukhan.openapi.AbstractTestWithoutDb
import com.marukhan.openapi.dao.EmployeesTest
import com.marukhan.openapi.model.request.Employee
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class EmployeeMapperTest : AbstractTestWithoutDb() {
		@Autowired
		lateinit var mapper: EmployeeMapper

		@Test
		fun `test toDto`() {
				val res = mapper.toDto(EmployeesTest.employee1)
				Assertions.assertEquals(res.firstName, EmployeesTest.employee1.firstName)
				Assertions.assertEquals(res.lastName, EmployeesTest.employee1.lastName)
		}

		@Test
		fun `test fromDto`() {
				val emp = EmployeesTest.employee1
				val employee: Employee = Employee(
					organizationId = emp.organization.id.toString(),
					firstName = emp.firstName,
					lastName = emp.lastName,
					jobTitle = emp.jobTitle
				)
				val res = mapper.fromDto(employee, emp.organization)
				Assertions.assertEquals(res.firstName, emp.firstName)
				Assertions.assertEquals(res.lastName, emp.lastName)
		}
}