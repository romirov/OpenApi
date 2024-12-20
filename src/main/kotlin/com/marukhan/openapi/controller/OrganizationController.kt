package com.marukhan.openapi.controller

import com.marukhan.openapi.api.request.OrganizationApi
import com.marukhan.openapi.model.request.Organization
import com.marukhan.openapi.service.OrganizationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class OrganizationController : OrganizationApi {
    @Autowired
    lateinit var organizationService: OrganizationService

    override fun addOrganization(organization: Organization?): ResponseEntity<Organization> =
      ResponseEntity.ok(organization?.let {organizationService.save(it)})

    override fun updateOrganizationById(organizationId: String, organizationName: String): ResponseEntity<Organization> =
            ResponseEntity.ok(organizationService.update(Organization(organizationId, organizationName)))

    override fun deleteOrganizationById(organizationId: String): ResponseEntity<Unit> {
        organizationService.deleteById(organizationId)
        return ResponseEntity.ok().build()
    }

    override fun deleteOrganizations(): ResponseEntity<Unit> {
        organizationService.deleteAll()
        return ResponseEntity.ok().build()
    }

    override fun getOrganizationById(organizationId: String): ResponseEntity<Organization> =
            ResponseEntity.ok(organizationService.findById(organizationId))

    override fun getOrganizations(): ResponseEntity<List<Organization>> =
            ResponseEntity.ok(organizationService.findAll())
}