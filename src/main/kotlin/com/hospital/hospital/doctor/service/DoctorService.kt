package com.hospital.hospital.doctor.service

import com.hospital.hospital.doctor.dtos.DoctorDto
import com.hospital.hospital.doctor.model.Doctor
import com.hospital.hospital.doctor.repository.DoctorRepository
import com.hospital.hospital.security.model.Role
import com.hospital.hospital.security.model.User
import com.hospital.hospital.security.model.UserRole
import com.hospital.hospital.security.repository.RoleRepository
import com.hospital.hospital.security.repository.UserRepository
import com.hospital.hospital.security.repository.UserRoleRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class DoctorService(private val doctorRepository: DoctorRepository,private val userRepository: UserRepository, private val roleRepository: RoleRepository, private val userRoleRepository: UserRoleRepository) {
    /**+
     * A method to submit doctor data and save it
     * @param body
     * @return Doctor
     */
    fun submitDto(body: DoctorDto): Doctor {
        //Save the data in User Table
        var user = User()
        user.email=body.emailAddress
        user.username=body.username
        user.password = body.password
        user.principalRole="doctor"
        var userDetails = this.userRepository.save(user)

        //Prepare roles for users
        var roles : List<Role>? = null
        var role : Role? = roleRepository.findByRoleName("doctor")
        roles = ArrayList<Role>()
        if (role != null) {
            roles.add(role)
        }
        var roleDefault : Role? = roleRepository.findByRoleName("user")
        if (roleDefault != null) {
            roles.add(roleDefault)
        }

        //Assign roles to the user
        for (role in roles) {
            var userRole = UserRole()
            userRole.userId = userDetails.id.toString()
            userRole.userId.toLong()
            userRole.roleId = role.id.toString()
            userRole.roleId.toLong()
            this.userRoleRepository.save(userRole)
        }

        //Save data in doctors table
       var doctor = Doctor()
        doctor.firstName = body.firstName
        doctor.lastName=body.lastName
        doctor.address=body.address
        doctor.phoneNumber=body.phoneNumber
        doctor.userId= userDetails.id.toString()
        doctor.userId.toLong()
        return this.doctorRepository.save(doctor)
    }


    /**+
     * A method to drop doctor
     * @param doctorUserId
     * @return Doctor
     */
    fun dropDoctor(doctorUserId : Long):Doctor{
        this.userRepository.deleteById(doctorUserId)
        var roles : List<UserRole> = userRoleRepository.getByUserId(doctorUserId)
        if (roles.isNotEmpty()){
            this.userRoleRepository.deleteAll(roles)
        }
        return this.doctorRepository.deleteByUserId(doctorUserId)
    }
}