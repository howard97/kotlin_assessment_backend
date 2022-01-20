package com.hospital.hospital.patient.controller

import com.example.auth.dtos.MessageDto
import com.hospital.hospital.patient.dtos.AppointmentDto
import com.hospital.hospital.patient.service.PatientService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.lang.Boolean

@RestController
@RequestMapping("/api/v1")
class PatientController(private val patientService: PatientService) {

    /**+
     * an End point to schedule an appointment
     * @param body
     * @return responseEntity<Any>
     */
    @PostMapping("/scheduleAppointment")
    fun scheduleAppointment(@RequestBody body : AppointmentDto): ResponseEntity<Any> {
        try{
            var id = body.patientId.toString()
            /*if(this.patientService.checkIfUserHasAnExistingAppointment(id.toLong())){
                return ResponseEntity.badRequest().body(MessageDto("You currently have an existing appointment, Kindly wait for the doctors to respond"))
            }*/
            return ResponseEntity.ok(this.patientService.scheduleAppointment(body))
        }catch(e:Exception){
           return ResponseEntity.badRequest().body(MessageDto("The request to schedule an appointment failed"))
        }
    }
}