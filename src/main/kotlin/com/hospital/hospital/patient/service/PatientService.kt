package com.hospital.hospital.patient.service

import com.hospital.hospital.patient.dtos.AppointmentDto
import com.hospital.hospital.patient.model.Appointment
import com.hospital.hospital.patient.repository.AppointmentRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Service
class PatientService(private val appointmentRepository: AppointmentRepository) {
    /**+
     * A method to schedule an appointment
     * @param appointment
     * @return Appointment
     */
    fun scheduleAppointment(appointment: AppointmentDto):Appointment{
     var appoint = Appointment()
        val str = appointment.appointmentDate
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val dateTime = LocalDateTime.parse(str, formatter)
        appoint.appointmentDate = dateTime
        appoint.patientId = appointment.patientId
        appoint.appointName = appointment.appointmentName
        appoint.status = "SUBMITTED"
        return this.appointmentRepository.save(appoint)
    }

    /**+
     * A method to check If User Has An Existing Appointment
     * @param userId
     * @return Boolean
     */
    fun checkIfUserHasAnExistingAppointment(userId: Long):Boolean {
         val user = this.appointmentRepository.getByPatientId(userId)
        return user != null
        }


    /**+
     * A method to approve Appointment
     * @param appointment,id
     * @return Appointment
     */
    fun approveAppointment(appointment:AppointmentDto, id:Long):Appointment{
        var appoint = this.appointmentRepository.getByDoctorId(id)
        if(appoint != null){
            appoint.doctorId=appointment.doctorId
            appoint.status="APPROVED"
            this.appointmentRepository.save(appoint)
        }
        return appoint
    }

    /**+
     * A method to reschedule Appointment
     * @param appointment,id
     * @return Appointment
     */
    fun rescheduleAppointment(appointment:AppointmentDto, id:Long):Appointment{
        var appoint = this.appointmentRepository.getByDoctorId(id)
        if(appoint != null){
            val str = appointment.appointmentDate
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
            val dateTime = LocalDateTime.parse(str, formatter)
            appoint.appointmentDate = dateTime
            appoint.doctorId=appointment.doctorId
            appoint.status="RESCHEDULED"
            this.appointmentRepository.save(appoint)
        }
        return appoint
    }
}