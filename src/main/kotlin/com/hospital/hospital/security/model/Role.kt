package com.hospital.hospital.security.model

import javax.persistence.*

@Entity
@Table(name="ROLE")
class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    var id = 0L
    @Column(name = "ROLE_NAME")
    var roleName=""
}