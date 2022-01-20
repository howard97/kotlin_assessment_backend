package com.hospital.hospital

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude=[SecurityAutoConfiguration::class])
class HospitalApplication

fun main(args: Array<String>) {
	runApplication<HospitalApplication>(*args)
}
