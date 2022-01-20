package com.hospital.hospital.patient.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name="APPOINTMENT")
class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    val id = 0L
    @Column(name = "PATIENT_ID")
    var patientId = 0L
        get()=field
        set(value){
            field = value
        }
    @Column(name = "APPOINT_NAME")
    var appointName = ""
        get()=field
        set(value){
            field = value
        }
    @Column(name = "APPOINTMENT_DATE")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    var appointmentDate : LocalDateTime? = null
        get()=field
        set(value){
            field = value
        }
    @Column(name = "STATUS")
    var status = ""
        get()=field
        set(value){
            field = value
        }
    @Column(name = "DOCTOR_ID")
    var doctorId = 0L
        get()=field
        set(value){
            field = value
        }

}