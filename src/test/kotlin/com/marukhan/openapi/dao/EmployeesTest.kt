package com.marukhan.openapi.dao

import com.marukhan.openapi.dao.OrganizationTest.organizationEntity1
import com.marukhan.openapi.model.request.EmployeeRq
import com.marukhan.openapi.model.request.EmployeeRs
import java.util.UUID

object EmployeesTest {
	val employeeEntityId1 = UUID.fromString("91414700-2352-4926-b30c-7056f486ecd6")
	val employeeEntityId2 = UUID.fromString("b0b46f56-9c9f-423d-8091-6aaae815c06e")

	val employeeRq1 = EmployeeRq(
		organizationId = organizationEntity1.id.toString(),
		firstName = "Test1",
		lastName = "Test1",
		jobTitle = "Boss"
	)

	val employeeRq2 = EmployeeRq(
		organizationId = organizationEntity1.id.toString(),
		firstName = "Test2",
		lastName = "Test2",
		jobTitle = "Worker"
	)

	val employeeEntity1 = EmployeeEntity(
		id = employeeEntityId1,
		organization = organizationEntity1,
		firstName = "Test1",
		lastName = "Test1",
		jobTitle = "Boss"
	)

	val employeeEntity2 = EmployeeEntity(
		id = employeeEntityId2,
		organization = organizationEntity1,
		firstName = "Test2",
		lastName = "Test2",
		jobTitle = "Worker"
	)

	val employeeRs1 = EmployeeRs(
		id = employeeEntity1.id.toString(),
		organizationId = employeeEntity1.organization.id.toString(),
		firstName = employeeEntity1.firstName,
		lastName = employeeEntity1.lastName,
		jobTitle = employeeEntity1.jobTitle
	)

	val employeeRs2 = EmployeeRs(
		id = employeeEntity2.id.toString(),
		organizationId = employeeEntity2.organization.id.toString(),
		firstName = employeeEntity2.firstName,
		lastName = employeeEntity2.lastName,
		jobTitle = employeeEntity2.jobTitle
	)
}