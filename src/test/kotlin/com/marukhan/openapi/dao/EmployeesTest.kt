package com.marukhan.openapi.dao

object EmployeesTest {
//		private val organizationEntityId = UUID.fromString("b749d000-9a4f-4bb7-8235-3e47779df243")
//    private val employeeEntityId1 = UUID.fromString("e749d110-9a4f-4bb7-8235-3e41119df243")
//    private val employeeEntityId2 = UUID.fromString("z749d220-9a4f-4bb7-8235-3e42229df243")

		val employee1 =
			EmployeeEntity(organization = OrganizationTest.organization, firstName = "Test1", lastName = "Test1", jobTitle = "Boss")
		val employee2 =
			EmployeeEntity(organization = OrganizationTest.organization, firstName = "Test2`", lastName = "Test2", jobTitle = "Worker")

}