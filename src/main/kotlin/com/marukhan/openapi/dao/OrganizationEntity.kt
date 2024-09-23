package com.marukhan.openapi.dao

import jakarta.persistence.Entity
import java.util.*

@Entity
data class OrganizationEntity(
        val id: UUID,
        val organizationName: String
)
