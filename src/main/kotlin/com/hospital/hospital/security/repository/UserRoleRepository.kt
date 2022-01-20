package com.hospital.hospital.security.repository

import com.hospital.hospital.security.model.UserRole
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRoleRepository : JpaRepository<UserRole,Long>{
    fun getByUserId(doctorUserId: Long): List<UserRole>
}