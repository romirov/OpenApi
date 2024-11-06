package com.marukhan.openapi.repository

import com.marukhan.openapi.dao.OrganizationTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class OrganizationRepoTest: AbstractRepo() {
    @Autowired
    lateinit var repo: OrganizationRepository

    @Test
    fun `test for save`() {
        val res = repo.save(OrganizationTest.organization)
        Assertions.assertNotNull(res)
    }

    @Test
    @Disabled
    fun `test for update by Id`() {
        val res = repo.save(OrganizationTest.organization)
        val updRes = repo.save(res.copy(organizationName = "newName"))
        Assertions.assertEquals(updRes, res.copy(organizationName = "newName"))
    }

    @Test
    @Disabled
    fun `test for delete by Id`() {
        val res = repo.save(OrganizationTest.organization)
        repo.deleteById(res.id)
        val updRes = repo.findById(res.id)
        Assertions.assertNull(updRes)
    }

    @Test
    @Disabled
    fun `test for find by Id`() {
        val res = repo.save(OrganizationTest.organization)
        val updRes = repo.findById(res.id)
        Assertions.assertEquals(updRes, res)
    }

    @Test
    @Disabled
    fun `test for delete all`() {
        repo.save(OrganizationTest.organization)
        repo.deleteAll()
        val updRes = repo.findAll()
        Assertions.assertTrue(updRes.isEmpty())
    }
}