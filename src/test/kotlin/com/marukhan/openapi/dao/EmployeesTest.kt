package com.marukhan.openapi.dao

object EmployeesTest {
		val employee1 =
			EmployeeEntity(organization = OrganizationTest.organization, firstName = "Test1", lastName = "Test1", jobTitle = "Boss")
		val employee2 =
			EmployeeEntity(organization = OrganizationTest.organization, firstName = "Test2`", lastName = "Test2", jobTitle = "Worker")
}