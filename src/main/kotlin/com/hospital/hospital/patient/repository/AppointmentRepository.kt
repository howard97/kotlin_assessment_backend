package com.hospital.hospital.patient.repository

import com.hospital.hospital.patient.model.Appointment
import org.springframework.data.jpa.repository.JpaRepository

interface AppointmentRepository:JpaRepository<Appointment,Long>{
    fun getByPatientId(userId: Long): Appointment
    fun getByDoctorId(id: Long): Appointment
}