package com.marukhan.openapi.service

import com.marukhan.openapi.mapper.OrganizationMapper
import com.marukhan.openapi.model.request.Organization
import com.marukhan.openapi.repository.OrganizationRepository
import org.springframework.stereotype.Service

@Service
class OrganizationService(
        private val repository: OrganizationRepository,
        private val mapper: OrganizationMapper
) {
    fun save(organization: Organization): Organization {
        val organizationEntity = mapper.fromDto(organization)
        val organizationEntityFromDb = repository.save(organizationEntity)
        return mapper.toDto(organizationEntityFromDb)
    }

    fun update(organization: Organization): Organization {
        val organizationEntity = mapper.fromDto(organization)
        require(repository.existsById(organizationEntity.id))
        val organizationEntityFromDb = repository.save(organizationEntity)
        return mapper.toDto(organizationEntityFromDb)
    }

    fun deleteById(id: String) = repository.deleteById(mapper.stringToUuid(id))

    fun deleteAll() = repository.deleteAll()

    fun findAll(): List<Organization> = repository.findAll().map { mapper.toDto(it) }

    fun findById(id: String): Organization = repository.findById(mapper.stringToUuid(id)).let { mapper.toDto(it.orElseThrow()) }
}
