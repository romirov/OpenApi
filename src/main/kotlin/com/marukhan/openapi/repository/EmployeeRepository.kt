package com.marukhan.openapi.repository

import com.marukhan.openapi.dao.EmployeeEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Repository
interface EmployeeRepository: JpaRepository<EmployeeEntity, UUID>{
    @Transactional(propagation = Propagation.MANDATORY)
    fun save(employeeEntity: EmployeeEntity): EmployeeEntity

    @Transactional(propagation = Propagation.MANDATORY)
    fun updateById(employeeEntity: EmployeeEntity): EmployeeEntity

    @Transactional(propagation = Propagation.MANDATORY)
    fun deleteByEmployeeIdAndOrganizationId(employeeId: UUID, organizationId: UUID)

    @Transactional(propagation = Propagation.MANDATORY)
    override fun deleteAll()

    fun findByEmployeeIdAndOrganizationId(employeeId: UUID, organizationId: UUID): Optional<EmployeeEntity>
}