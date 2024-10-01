package com.marukhan.openapi.configuration

import com.marukhan.openapi.mapper.EmployeeMapper
import com.marukhan.openapi.mapper.OrganizationMapper
import com.marukhan.openapi.repository.EmployeeRepository
import com.marukhan.openapi.repository.OrganizationRepository
import com.marukhan.openapi.service.EmployeeService
import com.marukhan.openapi.service.OrganizationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableTransactionManagement
open class OpenApiConfiguration {
    @Autowired
    lateinit var organizationRepository: OrganizationRepository

    @Autowired
    lateinit var employeeRepository: EmployeeRepository

    @Autowired
    lateinit var organizationMapper: OrganizationMapper

    @Autowired
    lateinit var employeeMapper: EmployeeMapper

    @Bean
    open fun organizationService() = OrganizationService(organizationRepository, organizationMapper)

    @Bean
    open fun employeeService() = EmployeeService(employeeRepository, employeeMapper)

    @Bean
    open fun organizationMapper() = OrganizationMapper()

    @Bean
    open fun employeeMapper() = EmployeeMapper()
}