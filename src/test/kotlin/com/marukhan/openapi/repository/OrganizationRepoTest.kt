package com.marukhan.openapi.repository

import com.marukhan.openapi.AbstractTestWithDb
import com.marukhan.openapi.dao.OrganizationTest.organizationEntity1
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.support.TransactionTemplate

class OrganizationRepoTest : AbstractTestWithDb() {
	@Autowired
	lateinit var repo: OrganizationRepository

	@Autowired
	lateinit var txManager: TransactionTemplate

	@AfterEach
	fun resetAll() {
		txManager.execute {
			repo.deleteAll()
		}
	}

	@Test
	fun `test for save`() {
		txManager.execute {
			val res = repo.save(organizationEntity1)
			Assertions.assertNotNull(res)
		}
	}

	@Test
	fun `test for update by Id`() {
		val updRes = txManager.execute {
			val res = repo.save(organizationEntity1)
			repo.save(res.copy(organizationName = "newName"))
		}
		Assertions.assertEquals(updRes!!.organizationName, "newName")
	}

	@Test
	fun `test for delete by Id`() {
		txManager.execute {
			val res = repo.save(organizationEntity1)
			repo.deleteById(res.id)
			val updRes = repo.findById(res.id)
			Assertions.assertTrue(updRes.isEmpty)
		}
	}

	@Test
	fun `test for find by Id`() {
		txManager.execute {
			val res = repo.save(organizationEntity1)
			val updRes = repo.findById(res.id)
			Assertions.assertEquals(updRes.get(), res)
		}
	}

	@Test
	fun `test for delete all`() {
		txManager.execute {
			repo.save(organizationEntity1)
			Assertions.assertTrue(repo.findAll().isNotEmpty())
			repo.deleteAll()
			Assertions.assertTrue(repo.findAll().isEmpty())
		}
	}
}