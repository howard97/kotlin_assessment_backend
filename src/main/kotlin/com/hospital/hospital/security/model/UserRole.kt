package com.hospital.hospital.security.model

import javax.persistence.*


@Entity
@Table(name="USER_ROLE")
class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    var id=0L
    @Column(name = "Role_ID")
    var roleId = ""

    @Column(name = "USER_ID")
    var userId = ""
}