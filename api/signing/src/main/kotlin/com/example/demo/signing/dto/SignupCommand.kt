package com.example.demo.signing.dto

import com.example.demo.model.User.LoginType

data class SignupCommand(
    val name: String,
    val age: Long,
    val loginType: LoginType,
    val password: String? = null,
)
