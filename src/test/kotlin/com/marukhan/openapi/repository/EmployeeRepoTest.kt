package com.marukhan.openapi.repository

import com.marukhan.openapi.AbstractTestWithDb
import com.marukhan.openapi.dao.EmployeesTest.employeeEntity1
import com.marukhan.openapi.dao.EmployeesTest.employeeEntity2
import com.marukhan.openapi.dao.OrganizationTest.organizationEntity1
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.support.TransactionTemplate

class EmployeeRepoTest : AbstractTestWithDb() {
	@Autowired
	lateinit var repo: EmployeeRepository

	@Autowired
	lateinit var txManager: TransactionTemplate

	@Autowired
	lateinit var repoOrg: OrganizationRepository

	@AfterEach
	fun resetAll() {
		txManager.execute {
			repoOrg.deleteAll()
			repo.deleteAll()
		}
	}

	@Test
	fun `test for save`() {
		val res = txManager.execute {
			repoOrg.save(organizationEntity1)
			repo.save(employeeEntity1)
		}

		Assertions.assertEquals(res?.id, employeeEntity1.id)
	}

	@Test
	fun `test for update by Id`() {
		val updRes = txManager.execute {
			repoOrg.save(organizationEntity1)
			repo.save(employeeEntity1)
			repo.save(employeeEntity1.copy(firstName = "newFirstName", lastName = "newLastName"))
		}
		Assertions.assertEquals(updRes?.firstName, "newFirstName")
		Assertions.assertEquals(updRes?.lastName, "newLastName")
	}

	@Test
	fun `test for find by Id`() {
		txManager.execute {
			repoOrg.save(organizationEntity1)
			repo.save(employeeEntity1)
		}
		txManager.execute {
			Assertions.assertNotNull(repo.findById(employeeEntity1.id))
		}
	}

	@Test
	fun `test for delete all`() {
		txManager.execute {
			repoOrg.save(organizationEntity1)
			repo.save(employeeEntity1)
			repo.save(employeeEntity2)
		}
		Assertions.assertTrue(repo.findAll().size == 2)
		txManager.execute {
			repo.deleteAll()
		}
		Assertions.assertTrue(repo.findAll().isEmpty())
	}
}