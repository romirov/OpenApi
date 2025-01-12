package com.marukhan.openapi.controller

import com.marukhan.openapi.api.request.OrganizationApi
import com.marukhan.openapi.model.request.OrganizationRq
import com.marukhan.openapi.model.request.OrganizationRs
import com.marukhan.openapi.service.OrganizationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class OrganizationController : OrganizationApi {
	@Autowired
	lateinit var organizationService: OrganizationService

	override fun addOrganization(organizationRq: OrganizationRq): ResponseEntity<OrganizationRs> =
		ResponseEntity.ok(
			organizationService.save(organizationRq)
		)

	override fun updateOrganizationById(organizationId: String, organizationRq: OrganizationRq): ResponseEntity<OrganizationRs> =
		ResponseEntity.ok(
			organizationService.update(organizationId, organizationRq)
		)

	override fun deleteOrganizationById(organizationId: String): ResponseEntity<Unit> {
		organizationService.deleteById(organizationId)
		return ResponseEntity.ok().build()
	}

	override fun deleteOrganizations(): ResponseEntity<Unit> {
		organizationService.deleteAll()
		return ResponseEntity.ok().build()
	}

	override fun getOrganizationById(organizationId: String): ResponseEntity<OrganizationRs> =
		ResponseEntity.ok(organizationService.findById(organizationId))

	override fun getOrganizations(): ResponseEntity<List<OrganizationRs>> =
		ResponseEntity.ok(organizationService.findAll())
}