package com.marukhan.openapi.repository

import com.marukhan.openapi.dao.EmployeeEntity
import com.marukhan.openapi.dao.EmployeesTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class EmployeeRepoTest : AbstractRepo() {
		@Autowired
		lateinit var repo: EmployeeRepository

		@Autowired
		lateinit var repoOrg: OrganizationRepository

		@Test
		fun `test for save`() {
				val res = repo.save(EmployeesTest.employee1)
				Assertions.assertEquals(res, EmployeesTest.employee1)
		}

		@Test
		@Disabled
		fun `test for update by Id`() {
				val res = repo.save<EmployeeEntity>(EmployeesTest.employee1)
				val updRes = repo.save(res.copy(firstName = "newFirstName", lastName = "newLastName"))
				Assertions.assertEquals(updRes, res.copy(firstName = "newFirstName", lastName = "newLastName"))
		}

		@Test
		@Disabled
		fun `test for find by Id`() {
				val res = repo.save<EmployeeEntity>(EmployeesTest.employee1)
				val updRes = repo.findById(res.id)
				Assertions.assertEquals(updRes, res)
		}

		@Test
		@Disabled
		fun `test for delete all`() {
				repo.save<EmployeeEntity>(EmployeesTest.employee1)
				repo.save<EmployeeEntity>(EmployeesTest.employee2)
				repo.deleteAll()
				val updRes = repo.findAll()
				Assertions.assertTrue(updRes.isEmpty())
		}
}