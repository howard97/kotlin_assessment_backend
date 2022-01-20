package com.hospital.hospital.security.service

import com.hospital.hospital.patient.model.Patient
import com.hospital.hospital.patient.repository.PatientRepository
import com.hospital.hospital.security.dtos.RegisterDto
import com.hospital.hospital.security.model.User
import com.hospital.hospital.security.model.UserRole
import com.hospital.hospital.security.repository.RoleRepository
import com.hospital.hospital.security.repository.UserRepository
import com.hospital.hospital.security.repository.UserRoleRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository, private val patientRepository: PatientRepository, private val userRoleRepository: UserRoleRepository, private val roleRepository: RoleRepository) {
    /**+
     * Method to Validate if user exists
     * @param email
     * @return Boolean
     */
    fun checkIfUseExists(email: String):Boolean{
        var user = this.userRepository.findByEmail(email)
        return user != null
    }


    /**+
     * Method to register patient data
     * @param body
     * @return user
     */
    fun save(body: RegisterDto):User{
        //save user data
        val user = User()
        user.username=body.username
        user.email=body.email
        user.principalRole="user"
        user.password=body.password
        var userDetails = this.userRepository.save(user)

        //save patient data
        var patient = Patient()
        patient.firstName = body.firstName
        patient.lastName = body.lastName
        patient.address =body.address
        patient.phoneNumber = body.phoneNumber
        this.patientRepository.save(patient)

        //Give a user a role
        val role = this.roleRepository.findByRoleName("user")
        var userRole = UserRole()
        if (role != null) {
            userRole.roleId = role.id.toString()
            userRole.roleId.toLong()

            userRole.userId = userDetails.id.toString()
            userRole.userId.toLong()
            this.userRoleRepository.save(userRole)
        }

        return userDetails
    }

    /**+
     * Method to find user by email
     * @param email
     * @return User
     */
    fun findByEmail(email:String):User?{
        return this.userRepository.findByEmail(email)
    }

    /**+
     * Method to get user by id
     * @param id
     * @return User
     */
    fun getById(id:Long): User {
        return this.userRepository.getById(id)
    }
}