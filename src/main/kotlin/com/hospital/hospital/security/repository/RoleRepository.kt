package com.hospital.hospital.security.repository

import com.hospital.hospital.security.model.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository:JpaRepository<Role,Long> {
    fun findByRoleName(name: String): Role?
}