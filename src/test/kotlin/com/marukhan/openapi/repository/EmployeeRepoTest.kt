package com.marukhan.openapi.repository

import com.marukhan.openapi.dao.EmployeeEntity
import com.marukhan.openapi.dao.EmployeesTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class EmployeeRepoTest : AbstractRepo() {
    @Autowired
    lateinit var repo: EmployeeRepository

    @Test
    fun `test for save`() {
        val res = repo.save<EmployeeEntity>(EmployeesTest.employee1)
        Assertions.assertEquals(res, EmployeesTest.employee1)
    }

    @Test
    fun `test for update by Id`() {
        val res = repo.save<EmployeeEntity>(EmployeesTest.employee1)
        val updRes = repo.updateById(res.copy(firstName = "newFirstName", lastName = "newLastName"))
        Assertions.assertEquals(updRes, res.copy(firstName = "newFirstName", lastName = "newLastName"))
    }

    @Test
    fun `test for delete by Id`() {
        val res = repo.save<EmployeeEntity>(EmployeesTest.employee1)
        repo.deleteByEmployeeIdAndOrganizationId(res.id, res.organizationId)
        val updRes = repo.findByEmployeeIdAndOrganizationId(res.id, res.organizationId)
        Assertions.assertNull(updRes)
    }

    @Test
    fun `test for find by Id`() {
        val res = repo.save<EmployeeEntity>(EmployeesTest.employee1)
        val updRes = repo.findByEmployeeIdAndOrganizationId(res.id, res.organizationId)
        Assertions.assertEquals(updRes, res)
    }

    @Test
    fun `test for delete all`() {
        repo.save<EmployeeEntity>(EmployeesTest.employee1)
        repo.save<EmployeeEntity>(EmployeesTest.employee2)
        repo.deleteAll()
        val updRes = repo.findAll()
        Assertions.assertTrue(updRes.isEmpty())
    }
}