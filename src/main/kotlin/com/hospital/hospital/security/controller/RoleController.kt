package com.hospital.hospital.security.controller

import com.example.auth.dtos.MessageDto
import com.hospital.hospital.security.dtos.RoleDto
import com.hospital.hospital.security.model.Role
import com.hospital.hospital.security.service.RoleService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class RoleController(private val roleService: RoleService) {
    /**+
     * An endpoint to configure roles
     * @param body
     * @return ResponseEntity<Any>
     */
    @PostMapping("/configureRole")
    fun configureRoles(@RequestBody body: RoleDto): ResponseEntity<Any> {
        return try {
            var role = Role()
            role.roleName = body.name
            ResponseEntity.ok(this.roleService.configureRoles(role))
        }catch(e: Exception){
            ResponseEntity.badRequest().body(MessageDto("Failed to configures role"))
        }
    }


    //A method to get all roles
    @GetMapping("/configuredRoles")
    fun getAllRoles():ResponseEntity<List<Role>>{
        return ResponseEntity.ok(this.roleService.getAllRoles())
    }


}