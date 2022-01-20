package com.hospital.hospital.patient.repository

import com.hospital.hospital.patient.model.Patient
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PatientRepository:JpaRepository<Patient,Long> {
}