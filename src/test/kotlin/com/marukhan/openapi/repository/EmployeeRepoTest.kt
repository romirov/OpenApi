package com.marukhan.openapi.repository

import com.marukhan.openapi.AbstractTestWithDb
import com.marukhan.openapi.dao.EmployeesTest
import com.marukhan.openapi.dao.OrganizationTest
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
						repoOrg.save(OrganizationTest.organization)
						repo.save(EmployeesTest.employee1)
				}

				Assertions.assertEquals(res?.id, EmployeesTest.employee1.id)
		}

		@Test
		fun `test for update by Id`() {
				val updRes = txManager.execute {
						repoOrg.save(OrganizationTest.organization)
						repo.save(EmployeesTest.employee1)
						repo.save(EmployeesTest.employee1.copy(firstName = "newFirstName", lastName = "newLastName"))
				}
				Assertions.assertEquals(updRes?.firstName, "newFirstName")
				Assertions.assertEquals(updRes?.lastName, "newLastName")
		}

		@Test
		fun `test for find by Id`() {
				txManager.execute {
						repoOrg.save(OrganizationTest.organization)
						repo.save(EmployeesTest.employee1)
				}
				txManager.execute {
						Assertions.assertNotNull(repo.findById(EmployeesTest.employee1.id))
				}
		}

		@Test
		fun `test for delete all`() {
				txManager.execute {
						repoOrg.save(OrganizationTest.organization)
						repo.save(EmployeesTest.employee1)
						repo.save(EmployeesTest.employee2)
				}
				Assertions.assertTrue(repo.findAll().size == 2)
				txManager.execute {
						repo.deleteAll()
				}
				Assertions.assertTrue(repo.findAll().isEmpty())
		}
}