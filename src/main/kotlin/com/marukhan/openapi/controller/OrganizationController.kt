package com.marukhan.openapi.controller

import com.marukhan.openapi.api.request.OrganizationApi
import com.marukhan.openapi.model.request.Organization
import org.springframework.http.ResponseEntity

class OrganizationController : OrganizationApi {
    override fun addOrganization(organizationName: String): ResponseEntity<Organization> {
        TODO("Not yet implemented")
    }

    override fun deleteOrganizationById(organizationId: Long): ResponseEntity<Unit> {
        TODO("Not yet implemented")
    }

    override fun deleteOrganizations(): ResponseEntity<Unit> {
        TODO("Not yet implemented")
    }

    override fun getOrganizationById(organizationId: String): ResponseEntity<Organization> {
        TODO("Not yet implemented")
    }

    override fun getOrganizations(): ResponseEntity<Unit> {
        TODO("Not yet implemented")
    }

    override fun updateOrganizationById(organizationId: String, organizationName: String): ResponseEntity<Organization> {
        TODO("Not yet implemented")
    }

}