package com.hospital.hospital.doctor.model

import javax.persistence.*

@Entity
@Table(name="DOCTOR")
class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    var id = 0L
    @Column(name = "FIRST_NAME")
    var firstName=""
    @Column(name = "LAST_NAME")
    var lastName = ""
    @Column(name = "PHONE_NUMBER")
    var phoneNumber=""
    @Column(name = "ADDRESS")
    var address=""

    @Column(name = "USER_ID")
    var userId = ""

}