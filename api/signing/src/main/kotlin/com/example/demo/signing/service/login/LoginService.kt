package com.example.demo.signing.service.login

import com.example.demo.model.User.LoginType
import com.example.demo.signing.dto.LoginRequest
import com.example.demo.signing.dto.LoginResponse

interface LoginService {
    fun login(loginRequest: LoginRequest): LoginResponse

    fun supports(): LoginType
}
