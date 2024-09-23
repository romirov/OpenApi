package com.marukhan.openapi.dao

import jakarta.persistence.Entity
import java.util.*

@Entity
data class EmployeeEntity (
    val id: UUID,
    val organizationId: UUID,
    val firstName: String,
    val lastName: String,
    val jobTitle: String,
)