package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class MultiDemoApplication

fun main(args: Array<String>) {
    runApplication<MultiDemoApplication>(*args)
}
