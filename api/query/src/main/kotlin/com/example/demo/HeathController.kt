package com.example.demo

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HeathController() {
    @Value("\${spring.profiles.active:none}")
    lateinit var activeProfiles: String

    @GetMapping("/health")
    fun health(): String = "$activeProfiles : Healthy!"
}
