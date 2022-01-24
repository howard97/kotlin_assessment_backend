package com.hospital.hospital.patient.service

import com.hospital.hospital.patient.dtos.AppointmentDto
import com.hospital.hospital.patient.model.Appointment
import com.hospital.hospital.patient.repository.AppointmentRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.format.DateTimeFormatterBuilder
import java.util.*


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

        val df = DateTimeFormatterBuilder()
            .parseCaseInsensitive() // add pattern
            .appendPattern("dd-MMM-yyyy") // create formatter (use English Locale to parse month names)
            .toFormatter(Locale.ENGLISH)
        /*val formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd")*/
        val dateTime = LocalDate.parse(str, df)
        appoint.appointmentDate = dateTime.atStartOfDay()
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
    fun checkIfUserHasAnExistingAppointment(userId: Long): Appointment {
        return appointmentRepository.getByPatientId(userId)
    }


    /**+
     * A method to approve Appointment
     * @param appointment,id
     * @return Appointment
     */
    fun approveAppointment(appointment:AppointmentDto, id:Long):Appointment{
        var appoint = this.appointmentRepository.getByPatientId(appointment.patientId)
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
        var appoint = this.appointmentRepository.getByPatientId(id)
        if(appoint != null){
            val str = appointment.appointmentDate
            val df = DateTimeFormatterBuilder()
                .parseCaseInsensitive() // add pattern
                .appendPattern("dd-MMM-yyyy") // create formatter (use English Locale to parse month names)
                .toFormatter(Locale.ENGLISH)
            /*val formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd")*/
            val dateTime = LocalDate.parse(str, df)
            appoint.appointmentDate = dateTime.atStartOfDay()
            appoint.doctorId=appointment.doctorId
            appoint.status="RESCHEDULED"
            this.appointmentRepository.save(appoint)
        }
        return appoint
    }
}