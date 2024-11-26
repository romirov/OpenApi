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
    @Column(name = "first_name")
    val firstName: String,
    @Column(name = "last_name")
    val lastName: String,
    @Column(name = "job_title")
    val jobTitle: String,
)