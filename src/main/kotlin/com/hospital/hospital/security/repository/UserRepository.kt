package com.hospital.hospital.security.repository

import com.hospital.hospital.security.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository:JpaRepository<User,Long> {
    fun findByEmail(email: String): User?
}