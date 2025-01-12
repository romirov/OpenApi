package com.marukhan.openapi.service

import com.marukhan.openapi.mapper.OrganizationMapper
import com.marukhan.openapi.model.request.OrganizationRq
import com.marukhan.openapi.model.request.OrganizationRs
import com.marukhan.openapi.repository.OrganizationRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class OrganizationService(
	private val repository: OrganizationRepository,
	private val mapper: OrganizationMapper
) {
	fun save(organizationRq: OrganizationRq): OrganizationRs {
		val organizationEntity = mapper.fromDto(organizationRq)
		val organizationEntityFromDb = repository.save(organizationEntity)
		return mapper.toDto(organizationEntityFromDb)
	}

	fun update(organizationId: String, organizationRq: OrganizationRq): OrganizationRs {
		val organizationEntity = repository.findById(
			UUID.fromString(organizationId)
		).orElseThrow()
		val organizationEntityFromDb = repository.save(
			organizationEntity.copy(organizationName = organizationRq.organizationName)
		)
		return mapper.toDto(organizationEntityFromDb)
	}

	fun deleteById(id: String) = repository.deleteById(mapper.stringToUuid(id))

	fun deleteAll() = repository.deleteAll()

	fun findAll(): List<OrganizationRs> = repository.findAll().map { mapper.toDto(it) }

	fun findById(id: String): OrganizationRs = repository
		.findById(mapper.stringToUuid(id))
		.let { mapper.toDto(it.orElseThrow()) }
}
