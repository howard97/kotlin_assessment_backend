package com.hospital.hospital.security.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import javax.persistence.*

@Entity
@Table(name="SEC_USER")
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id = 0L
    @Column(name = "USERNAME")
    var username=""
    @Column(name="EMAIL", unique = true)
    var email=""
    @Column(name="PRINCIPAL_ROLE", unique = true)
    var principalRole = ""
    @Column(name = "PASSWORD")
    var password=""
        @JsonIgnore
        get()=field
        set(value){
            val passwordEncoder= BCryptPasswordEncoder()
            field = passwordEncoder.encode(value)
        }

    fun comparePassword(password: String):Boolean{
        return BCryptPasswordEncoder().matches(password,this.password)
    }
}