package com.marukhan.openapi.repository

import com.marukhan.openapi.model.request.Organization
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface OrganizationRepository: JpaRepository<Organization, UUID>