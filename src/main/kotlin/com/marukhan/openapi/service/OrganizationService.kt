package com.marukhan.openapi.service

import com.marukhan.openapi.mapper.OrganizationMapper
import com.marukhan.openapi.model.request.Organization
import com.marukhan.openapi.repository.OrganizationRepository
import jakarta.transaction.Transactional


open class OrganizationService(
        private val repository: OrganizationRepository,
        private val mapper: OrganizationMapper
) {
    @Transactional
    fun save(organization: Organization): Organization {
        val organizationEntity = mapper.fromDto(organization)
        val organizationEntityFromDb = repository.save(organizationEntity)
        return mapper.toDto(organizationEntityFromDb)
    }

    @Transactional
    fun update(organization: Organization): Organization {
        val organizationEntity = mapper.fromDto(organization)
        val organizationEntityFromDb = repository.save(organizationEntity)
        return mapper.toDto(organizationEntityFromDb)
    }

    @Transactional
    fun deleteById(id: String) = repository.deleteById(mapper.stringToUuid(id))

    @Transactional
    fun deleteAll() = repository.deleteAll()

    fun findAll(): List<Organization> = repository.findAll().map { mapper.toDto(it) }

    fun findById(id: String): Organization = repository.findById(mapper.stringToUuid(id)).let { mapper.toDto(it.orElseThrow()) }
}
