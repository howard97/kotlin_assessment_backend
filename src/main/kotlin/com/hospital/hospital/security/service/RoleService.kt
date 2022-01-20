package com.hospital.hospital.security.service

import com.hospital.hospital.security.model.Role
import com.hospital.hospital.security.repository.RoleRepository
import org.springframework.stereotype.Service

@Service
class RoleService(private val roleRepository: RoleRepository) {
    /**+
     * A method to configure roles
     * @param role
     * @return Doctor
     */
    fun configureRoles(role: Role):Role{
        return this.roleRepository.save(role)
    }


    //get all configured roles
    fun getAllRoles():List<Role>{
        return this.roleRepository.findAll()
    }
}