package com.example.demo.signing.service

import com.example.demo.signing.dto.LoginRequest
import com.example.demo.signing.dto.LoginResponse
import com.example.demo.signing.dto.SignupCommand
import com.example.demo.signing.dto.SignupResponse
import com.example.demo.signing.service.login.LoginServiceFactory
import com.example.demo.signing.service.signup.SignupServiceFactory
import org.springframework.stereotype.Service

@Service
class SigningFacade(
    private val signupServiceFactory: SignupServiceFactory,
    private val loginServiceFactory: LoginServiceFactory,
) {
    fun login(loginRequest: LoginRequest): LoginResponse {
        return loginServiceFactory.getInstance(loginRequest.loginType).login(loginRequest)
    }

    fun signup(signupCommand: SignupCommand): SignupResponse {
        return signupServiceFactory.getInstance(signupCommand.loginType).signup(signupCommand)
    }
}
