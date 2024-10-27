package com.marukhan.openapi.dao

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "employees")
data class EmployeeEntity (
    @Id
    val id: UUID = UUID.randomUUID(),
    @ManyToOne
    val organization: OrganizationEntity,
    @Column
    val firstName: String,
    @Column
    val lastName: String,
    @Column
    val jobTitle: String,
)