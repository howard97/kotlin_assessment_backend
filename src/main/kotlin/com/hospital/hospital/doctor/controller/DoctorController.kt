package com.hospital.hospital.doctor.controller

import com.example.auth.dtos.MessageDto
import com.hospital.hospital.doctor.dtos.DoctorDto
import com.hospital.hospital.doctor.service.DoctorService
import com.hospital.hospital.patient.dtos.AppointmentDto
import com.hospital.hospital.patient.service.PatientService
import com.hospital.hospital.security.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class DoctorController(private val doctorService: DoctorService,private val userService: UserService, private val patientService: PatientService) {

    /**+
     * an End point to create a doctor user
     * @param body
     * @return responseEntity<Any>
     */
    @PostMapping("/createDoctor")
    fun createDoctorUser(@RequestBody body: DoctorDto):ResponseEntity<Any>{
        return try {
            //Check if Doctor has been exists
            var checkUser = this.userService.findByEmail(body.emailAddress)
            if(checkUser != null){
             ResponseEntity.badRequest().body(MessageDto("The Doctor Already exists in the system!!"))
            }
            ResponseEntity.ok(this.doctorService.submitDto(body))
        }catch(ex: Exception){
            ResponseEntity.badRequest().body(MessageDto("Could not save doctor data due to some error"))
        }
    }


    /**+
     * an End point to drop a doctor
     * @param id
     * @return responseEntity<Any>
     */
    @PostMapping("/dropDoctor/{id}")
    fun dropDoctor(@PathVariable id : String):ResponseEntity<Any> {
    return try{
        var newId = id.toLong()
        ResponseEntity.ok(this.doctorService.dropDoctor(newId))
    }catch(ex: Exception){
        ResponseEntity.badRequest().body(MessageDto("Could not drop the doctor!!"))
    }
    }


    /**+
     * an End point to approve appointment
     * @param body, id
     * @return responseEntity<Any>
     */
    @PostMapping("/approveAppointment/{id}")
    fun approveAppointment(@RequestBody body: AppointmentDto, @PathVariable id:String):ResponseEntity<Any>{
        return try {
            ResponseEntity.ok(this.patientService.approveAppointment(body, id.toLong()))
        }catch(ex: Exception){
            ResponseEntity.badRequest().body(MessageDto("Failed to Approve Appointment!!"))
        }
    }

    /**+
     * an End point to reschedule appointment
     * @param body
     * @return responseEntity<Any>
     */
    @PostMapping("/rescheduleAppointment/{id}")
    fun rescheduleAppointment(@RequestBody body: AppointmentDto, @PathVariable id:String):ResponseEntity<Any>{
        return try {
            ResponseEntity.ok(this.patientService.rescheduleAppointment(body, id.toLong()))
        }catch(ex: Exception){
            ResponseEntity.badRequest().body(MessageDto("Failed to reschedule Appointment!!"))
        }
    }



}