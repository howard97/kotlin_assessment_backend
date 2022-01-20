package com.hospital.hospital.patient.dtos

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate

class AppointmentDto {
    var patientId = 0L
    var doctorId=0L
    var appointmentName = ""
    var appointmentDate = ""
}