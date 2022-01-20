package com.hospital.hospital.doctor.repository

import com.hospital.hospital.doctor.model.Doctor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DoctorRepository: JpaRepository<Doctor,Long> {
    fun deleteByUserId(doctorUserId: Long): Doctor
}