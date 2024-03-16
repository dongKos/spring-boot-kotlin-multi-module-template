package com.example.demo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HeathController {

    @GetMapping("/health")
    fun health(): String = "Healthy!"
}
