package com.marukhan.openapi.dao

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "organizations")
data class OrganizationEntity(
	@Id
	val id: UUID = UUID.randomUUID(),
	@Column(name = "organization_name")
	val organizationName: String,
	@OneToMany(mappedBy = "organization")
	val employees: List<EmployeeEntity>
)
