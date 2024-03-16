package com.example.demo.dto

data class UserInfo(
    val id: Long,
    val name: String,
    val age: Long,
    val password: String? = null,
    val roles: List<String> = emptyList(),
)
