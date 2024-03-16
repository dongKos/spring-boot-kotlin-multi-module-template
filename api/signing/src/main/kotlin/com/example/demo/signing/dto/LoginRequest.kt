package com.example.demo.signing.dto

import com.example.demo.model.User.LoginType

data class LoginRequest(
    val userId: Long,
    val password: String? = null,
    val loginType: LoginType,
)
