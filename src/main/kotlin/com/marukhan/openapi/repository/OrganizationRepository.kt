package com.marukhan.openapi.repository

import com.marukhan.openapi.dao.OrganizationEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Repository
interface OrganizationRepository: JpaRepository<OrganizationEntity, UUID>{
    @Transactional(propagation = Propagation.MANDATORY)
    fun save(organizationEntity: OrganizationEntity): OrganizationEntity

    @Transactional(propagation = Propagation.MANDATORY)
    override fun deleteById(id: UUID)

    @Transactional(propagation = Propagation.MANDATORY)
    override fun deleteAll()
}