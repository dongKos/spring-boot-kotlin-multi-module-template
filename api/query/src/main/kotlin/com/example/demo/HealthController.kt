package com.example.demo

import io.swagger.v3.oas.annotations.Hidden
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@Hidden
@RestController
class HealthController {
    @Value("\${spring.profiles.active:none}")
    lateinit var activeProfiles: String

    @GetMapping("/health")
    fun health(): String = "$activeProfiles : Healthy!"
}
